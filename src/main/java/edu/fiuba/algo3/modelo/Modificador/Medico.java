package edu.fiuba.algo3.modelo.Modificador;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Jugador;

public class Medico extends Modificador {
    private final Jugador jugador;

    public Medico(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public void aplicarModificador() {
        Carta carta = this.jugador.agarrarDescarte();
        carta.usar();
    }
}
