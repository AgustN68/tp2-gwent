package edu.fiuba.algo3.modelo.Lector;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Carta.Carta;
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

import org.json.simple.parser.ParseException;

public class LectorJson implements Lector {

    // SE ASUME QUE LAS SECCIONES ESTAN EN EL ORDEN:
    private static final int POS_CUERPO_A_CUERPO = 0;
    private static final int POS_RANGO = 1;
    private static final int POS_ASEDIO = 2;
    //

    private static final int POS_SECCION_PRINCIPAL = 0;
    private static final int POS_MODIFICADOR = 0;

    // Por algun motivo las referencias del JSON difieren en ambos mazos
    private static final String REF_CUERPO_A_CUERPO = "Combate Cuerpo a Cuerpo";
    private static final String REF_RANGO = "Combate a Distancia";


    /* Hay modificadores y especiales que requieren:
       - secciones puntuales
       - Tablero
       - Jugador
       Por lo que se deben pasar como parametros al leer el mazo y por ende, a la fábrica
     */

    public List<Mazo> leerMazos (
            String ruta,
            List<Seccion> seccionesj1,
            List<Seccion> seccionesj2,
            Jugador jugador1,
            Jugador jugador2,
            Tablero tableroJ1,
            Tablero tableroJ2
            )
    {

        List<Mazo> mazos = new ArrayList<>();

        try {

            List<Unidad> unidadesJugador1 = new ArrayList<>();
            List<Especial> especialesJugador1 = new ArrayList<>();

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(ruta));

            JSONObject jsonObject = (JSONObject) obj;
            JSONObject mazoJugadorUno = (JSONObject) jsonObject.get("mazo_jugador_uno");
            JSONArray unidadesJugadorUno = (JSONArray) mazoJugadorUno.get("unidades");

            for (Object unidadObj : unidadesJugadorUno) {
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
                        jugador1,
                        obtenerListaSecciones(secciones, seccionesj1)
                        ); // Jugador por espia y medico. Las secciones por Agil.


                Seccion seccionPrincipal = obtenerSeccion(secciones.get(POS_SECCION_PRINCIPAL), seccionesj1);

                Unidad nuevaUnidad = UnidadFactory.crearUnidad(
                        nombre,
                        puntos,
                        seccionPrincipal,
                        nuevoModificador
                );

                nuevoModificador.setCarta(nuevaUnidad);
                unidadesJugador1.add(nuevaUnidad);
            }

            JSONArray especialesJugadorUno = (JSONArray) mazoJugadorUno.get("especiales");

            for (Object especialObj : especialesJugadorUno) {
                JSONObject especial = (JSONObject) especialObj;

                String nombre = (String) especial.get("nombre");
                String descripcion = (String) especial.get("descripcion");
                String tipo = (String) especial.get("tipo");

                JSONArray seccionesAfectadasArray = (JSONArray) especial.get("afectado"); // array

                List<String> seccionesAString = parsearJsonArrayDeSecciones(seccionesAfectadasArray);
                List<Seccion> seccionesAfectadas = parsearSeccionesDeAmbosJugadores(seccionesAString, seccionesj1, seccionesj2);


                Especial nuevaEspecial = EspecialFactory.crearEspecial(
                        tipo,
                        nombre,
                        descripcion,
                        tableroJ1,
                        tableroJ2,
                        seccionesAfectadas
                );

                especialesJugador1.add(nuevaEspecial);




            }
            mazos.add(new Mazo(unidadesJugador1, especialesJugador1));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return mazos;
    }


    private List<Seccion> parsearSeccionesDeAmbosJugadores
            (
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
        switch (nombre) {
            case REF_CUERPO_A_CUERPO:
                return seccion.get(POS_CUERPO_A_CUERPO);

            case REF_RANGO:
                return seccion.get(POS_RANGO);

            case "Cuerpo a Cuerpo":
                return seccion.get(POS_CUERPO_A_CUERPO);
            case "Rango":
                return seccion.get(POS_RANGO);
            case "Asedio":
                return seccion.get(POS_ASEDIO);

            default:
                throw new SeccionInexistenteException("Sección no reconocida: " + nombre);
        }
    }


    // Para agil y especiales
    private List<Seccion> obtenerListaSecciones(List<String> seccionesString, List<Seccion> seccionesJugador) {
        List<Seccion> secciones = new ArrayList<>();

        for (String seccion : seccionesString) {
            switch (seccion) {
                case REF_CUERPO_A_CUERPO:
                    secciones.add(seccionesJugador.get(POS_CUERPO_A_CUERPO));
                    break;

                case REF_RANGO:
                    secciones.add(seccionesJugador.get(POS_RANGO));
                    break;

                case "Cuerpo a Cuerpo":
                    secciones.add(seccionesJugador.get(POS_CUERPO_A_CUERPO));
                    break;
                case "Rango":
                    secciones.add(seccionesJugador.get(POS_RANGO));
                    break;
                case "Asedio":
                    secciones.add(seccionesJugador.get(POS_ASEDIO));
                    break;
                default:
                    throw new SeccionInexistenteException("Sección no reconocida: " + seccion);
            }
        }
        return secciones;

    }

}
