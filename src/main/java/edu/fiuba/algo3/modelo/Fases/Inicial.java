package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.Gwent;
import edu.fiuba.algo3.modelo.Lector.Lector;
import edu.fiuba.algo3.modelo.Lector.LectorJson;
import edu.fiuba.algo3.modelo.Mazo;
import java.util.List;

public class Inicial extends Fase {

    private static final String RUTA_JSON = "src/main/java/edu/fiuba/algo3/archivos/gwent.json";
    private Lector lector = new LectorJson();

    public Inicial(Gwent juego) {
        super(juego);
    }

    @Override
    public void iniciarFase() {
        seleccionarNombres();
        seleccionarMazos();
        juego.cambiarFase(new Preparacion(juego));
    }

    private void seleccionarMazos() {
        List <Mazo> mazos = lector.leerMazos(RUTA_JSON,jugador1,jugador2);
        jugador1.setMazo(mazos.get(0));
        jugador2.setMazo(mazos.get(1));
    }

    private void seleccionarNombres() {
        jugador1.setNombre("Jugador1");
        jugador2.setNombre("Jugador2");
    }

}
