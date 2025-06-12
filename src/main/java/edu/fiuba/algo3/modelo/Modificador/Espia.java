package edu.fiuba.algo3.modelo.Modificador;

import edu.fiuba.algo3.modelo.Jugador;

public class Espia extends Modificador {
    private final Jugador jugador;

    public Espia(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public void aplicarModificador() {
        jugador.tomarCartasEspia();
    }
}
