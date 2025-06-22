package edu.fiuba.algo3.modelo.Lector;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Factory.EspecialFactory;
import edu.fiuba.algo3.modelo.Factory.ModificadorFactory;
import edu.fiuba.algo3.modelo.Factory.UnidadFactory;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mazo;
import edu.fiuba.algo3.modelo.Modificador.Modificador;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.exceptions.SeccionInexistenteException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class LectorJson implements Lector {

    // SE ASUME QUE LAS SECCIONES ESTAN EN EL ORDEN:
    private static final int POS_CUERPO_A_CUERPO = 0;
    private static final int POS_RANGO = 1;
    private static final int POS_ASEDIO = 2;
    //

    private static final int POS_SECCION_PRINCIPAL = 0;
    private static final int POS_MODIFICADOR = 0;

    // Por algun motivo las referencias del JSON a secciones difieren en ambos mazos
    private static final String CUERPO_A_CUERPO = "Cuerpo a Cuerpo";
    private static final String RANGO = "Rango";
    private static final String ASEDIO = "Asedio";

    private static final String REF_CUERPO_A_CUERPO = "Combate Cuerpo a Cuerpo";
    private static final String REF_RANGO = "Combate a Distancia";

    private static final Map<String, Integer> SECCIONES_MAP = Map.of(
            REF_CUERPO_A_CUERPO, POS_CUERPO_A_CUERPO,
            REF_RANGO, POS_RANGO,
            CUERPO_A_CUERPO, POS_CUERPO_A_CUERPO,
            RANGO, POS_RANGO,
            ASEDIO, POS_ASEDIO
    );


    /* Hay modificadores y especiales que requieren:
       - secciones puntuales
       - Tablero
       - Jugador
       Por lo que se deben pasar como parametros al leer el mazo y por ende, a la fábrica
     */

    public List<Mazo> leerMazos (String ruta, Jugador jugador1, Jugador jugador2) {

        List<Mazo> mazos = new ArrayList<>();
        Tablero tableroJ1 = jugador1.getTablero();
        Tablero tableroJ2 = jugador2.getTablero();
        List<Seccion> seccionesJ1 = tableroJ1.getSecciones();
        List<Seccion> seccionesJ2 = tableroJ2.getSecciones();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(ruta));
            JSONObject jsonObject = (JSONObject) obj;

            // Procesar mazo del jugador 1
            Mazo mazoJugador1 = procesarMazo(
                jsonObject,
                "mazo_jugador_uno",
                seccionesJ1,
                seccionesJ2,
                jugador1,
                tableroJ1,
                tableroJ2
            );
            mazos.add(mazoJugador1);

            // Procesar mazo del jugador 2
            Mazo mazoJugador2 = procesarMazo(
                jsonObject,
                "mazo_jugador_dos",
                seccionesJ2,
                seccionesJ1,
                jugador2,
                tableroJ2,
                tableroJ1
            );
            mazos.add(mazoJugador2);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return mazos;
    }

    private Mazo procesarMazo(
            JSONObject jsonObject,
            String nombreMazo,
            List<Seccion> seccionesJugadorActual,
            List<Seccion> seccionesOtroJugador,
            Jugador jugadorActual,
            Tablero tableroJugadorActual,
            Tablero tableroOtroJugador
    ) {
        List<Unidad> unidades = new ArrayList<>();
        List<Especial> especiales = new ArrayList<>();

        JSONObject mazoJson = (JSONObject) jsonObject.get(nombreMazo);

        // Procesar unidades
        JSONArray unidadesJson = (JSONArray) mazoJson.get("unidades");
        for (Object unidadObj : unidadesJson) {
            JSONObject unidad = (JSONObject) unidadObj;

            String nombre = (String) unidad.get("nombre");
            Long puntos = (Long) unidad.get("puntos");
            String seccionesString = (String) unidad.get("seccion");

            List<String> secciones = parsearSecciones(seccionesString);

            JSONArray modificadores = (JSONArray) unidad.get("modificador");

            String modificador = "SinModificador";

            if (!modificadores.isEmpty()) {
                modificador = (String) modificadores.get(POS_MODIFICADOR);
            }

            Modificador nuevoModificador = ModificadorFactory.crearModificador(
                    modificador,
                    jugadorActual,
                    obtenerListaSecciones(secciones, seccionesJugadorActual)
            );

            Seccion seccionPrincipal = obtenerSeccion(secciones.get(POS_SECCION_PRINCIPAL), seccionesJugadorActual);

            Unidad nuevaUnidad = UnidadFactory.crearUnidad(
                    nombre,
                    puntos,
                    seccionPrincipal,
                    nuevoModificador
            );

            nuevoModificador.setCarta(nuevaUnidad);
            unidades.add(nuevaUnidad);
        }

        // Procesar especiales
        JSONArray especialesJson = (JSONArray) mazoJson.get("especiales");
        for (Object especialObj : especialesJson) {
            JSONObject especial = (JSONObject) especialObj;

            String nombre = (String) especial.get("nombre");
            String descripcion = (String) especial.get("descripcion");
            String tipo = (String) especial.get("tipo");

            JSONArray seccionesAfectadasArray = (JSONArray) especial.get("afectado");
            List<String> seccionesAString = parsearJsonArrayDeSecciones(seccionesAfectadasArray);
            List<Seccion> seccionesAfectadas = parsearSeccionesDeAmbosJugadores(
                    seccionesAString,
                    seccionesJugadorActual,
                    seccionesOtroJugador
            );

            Especial nuevaEspecial = EspecialFactory.crearEspecial(
                    tipo,
                    nombre,
                    descripcion,
                    tableroJugadorActual,
                    tableroOtroJugador,
                    seccionesAfectadas
            );

            especiales.add(nuevaEspecial);
        }

        return new Mazo(unidades, especiales);
    }


    private List<Seccion> parsearSeccionesDeAmbosJugadores(
            List<String> seccionesAfectadas,
            List<Seccion> seccionesj1,
            List<Seccion> seccionesj2
            )
    {
        List<Seccion> nuevasSecciones = new ArrayList<>();

        for (String seccion : seccionesAfectadas) {
            nuevasSecciones.add(obtenerSeccion(seccion, seccionesj1));
            nuevasSecciones.add(obtenerSeccion(seccion, seccionesj2));
        }

        return nuevasSecciones;
    }


    private List<String> parsearSecciones(String seccionString) {
        String[] seccionesArray = seccionString.split(",");
        List<String> secciones = new ArrayList<>();

        for (String seccion : seccionesArray) {
            secciones.add(seccion.trim());
        }

        return secciones;
    }

    private List<String> parsearJsonArrayDeSecciones(JSONArray jsonArray) {

        List<String> seccionesAfectadas = new ArrayList<>();

        if (jsonArray != null) {
            for (Object seccionObj : jsonArray) {
                if (seccionObj != null) {
                    seccionesAfectadas.add((String) seccionObj);
                }
            }
        }
        return seccionesAfectadas;
    }

    private Seccion obtenerSeccion (String nombre, List<Seccion> seccion) {
        if (!SECCIONES_MAP.containsKey(nombre)) {
            throw new SeccionInexistenteException("Sección no reconocida: " + nombre);
        } else {
            int pos = SECCIONES_MAP.get(nombre);
            return seccion.get(pos);
         }
        }

    // Para agil y especiales
    private List<Seccion> obtenerListaSecciones(List<String> seccionesString, List<Seccion> seccionesJugador) {
        List<Seccion> secciones = new ArrayList<>();

        for (String seccion : seccionesString) {

            if (!SECCIONES_MAP.containsKey(seccion)) {
                throw new SeccionInexistenteException("Sección no reconocida: " + seccion);
            } else {
                int pos = SECCIONES_MAP.get(seccion);
                secciones.add(seccionesJugador.get(pos));
            }
        }

        return secciones;

    }

}
