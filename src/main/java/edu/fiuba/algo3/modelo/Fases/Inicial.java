package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Lector.Lector;
import edu.fiuba.algo3.modelo.Lector.LectorJson;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;

import java.util.ArrayList;
import java.util.List;

public class Inicial extends Fase {

    private static final String RUTA_JSON = "src/main/java/edu/fiuba/algo3/archivos/gwent.json";
    private static final int CANT_JUGADORES = 2;

    private Lector lector = new LectorJson();

    public Inicial(Gwent juego) {
        super(juego);
    }


    @Override
    public void iniciarFase() {
        prepararJugadores();
        juego.setJugadores(jugadores);
        juego.cambiarFase(new Preparacion(juego));
    }

    private void prepararJugadores() {
        crearJugadores();

        List <Mazo> mazos = lector.leerMazos(RUTA_JSON, jugadores.get(0), jugadores.get(1));

        int indice = 0;
        for (Jugador jugador : jugadores) {
            jugador.setMazo(mazos.get(indice));
            jugador.setNombre("Jugador " + indice);
            indice++;
        }
    }

    private void crearJugadores() {
        for (int i = 0; i < CANT_JUGADORES; i++) {
            jugadores.add(new Jugador(crearTablero(),new PilaDeDescarte()));
        }
    }

    private Tablero crearTablero() {
        List<Seccion> secciones = new ArrayList<>();
        secciones.add(new CuerpoACuerpo());
        secciones.add(new Rango());
        secciones.add(new Asedio());

        Tablero tablero = new Tablero(secciones);

        return tablero;

    }
}
