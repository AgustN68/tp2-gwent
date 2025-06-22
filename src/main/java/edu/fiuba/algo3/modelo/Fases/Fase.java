package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.Gwent;
import edu.fiuba.algo3.modelo.Jugador;

public abstract class Fase {
    Jugador jugador1;
    Jugador jugador2;
    Gwent juego;

    public Fase(Gwent juego) {
        this.juego = juego;
    }

    public abstract void iniciarFase();

}
