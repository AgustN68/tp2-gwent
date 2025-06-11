package edu.fiuba.algo3.modelo.Modificador;

import edu.fiuba.algo3.modelo.Jugador;

public class Medico extends Modificador {
    private final Jugador jugador;

    public Medico(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public void aplicarModificador() {
    }
}
