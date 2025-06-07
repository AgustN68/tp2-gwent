package edu.fiuba.algo3.modelo.Modificador;

import edu.fiuba.algo3.modelo.Jugador;

public class Espia extends Modificador {

    private Jugador jugador;

    public Espia(Jugador jugador) {
        this.jugador = jugador;

    }

    @Override
    public void aplicarModificador() {
        jugador.tomarCartasMazo(2);
    }
}
