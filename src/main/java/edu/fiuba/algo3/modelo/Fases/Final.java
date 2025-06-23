package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.Gwent;
import edu.fiuba.algo3.modelo.Jugador;

public class Final extends Fase {

    private Jugador ganador;

    public Final(Gwent juego, Jugador ganador) {
        super(juego);
        this.ganador = ganador;
    }

    @Override
    public void iniciarFase() {
        System.out.println("El ganador es: " + ganador.getNombre());
    }
}
