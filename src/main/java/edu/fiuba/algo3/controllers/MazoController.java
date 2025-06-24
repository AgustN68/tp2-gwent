package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mazo;
import edu.fiuba.algo3.modelo.Lector.LectorJson;
import edu.fiuba.algo3.modelo.Lector.Lector;

import java.util.List;

/**
 * Controlador para manejar la carga de mazos desde archivos JSON
 */
public class MazoController {

    private Lector lectorJson;
    private static final String RUTA_JSON = "src/main/java/edu/fiuba/algo3/archivos/gwent.json";

    public MazoController() {
        this.lectorJson = new LectorJson();
    }

    /**
     * Carga los mazos para ambos jugadores utilizando las instancias reales
     * @param jugador1 El jugador 1 real
     * @param jugador2 El jugador 2 real
     */
    public void cargarMazos(Jugador jugador1, Jugador jugador2) {
        List<Mazo> mazos = lectorJson.leerMazos(RUTA_JSON, jugador1, jugador2);
        Mazo mazoJ1 = mazos.get(0);
        Mazo mazoJ2 = mazos.get(1);

        jugador1.setMazo(mazoJ1);
        jugador2.setMazo(mazoJ2);
    }
}
