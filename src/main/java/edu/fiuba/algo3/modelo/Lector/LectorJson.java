package edu.fiuba.algo3.modelo.Lector;

import java.io.FileReader;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Factory.ModificadorFactory;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LectorJson implements Lector {

    public void leerMazos(String ruta) {

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(ruta));

            JSONObject jsonObject = (JSONObject) obj;

            JSONObject mazoJugadorUno = (JSONObject) jsonObject.get("mazo_jugador_uno");

            JSONArray unidadesJugadorUno = (JSONArray) mazoJugadorUno.get("unidades");

            for (Object unidadObj : unidadesJugadorUno) {
                JSONObject unidad = (JSONObject) unidadObj;

                String nombre = (String) unidad.get("nombre");
                Long puntos = (Long) unidad.get("puntos");
                String seccion = (String) unidad.get("seccion");
                JSONArray modificadores = (JSONArray) unidad.get("modificador"); // array

            }

            JSONArray especialesJugadorUno = (JSONArray) mazoJugadorUno.get("especiales");

            for (Object especialObj : especialesJugadorUno) {
                JSONObject especial = (JSONObject) especialObj;

                String nombre = (String) especial.get("nombre");
                String descripcion = (String) especial.get("descripcion");
                String tipo = (String) especial.get("tipo");
                JSONArray seccionesAfectadas = (JSONArray) especial.get("afectado"); // array

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
