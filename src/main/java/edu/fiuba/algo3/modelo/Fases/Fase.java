package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.Gwent;

public abstract class Fase {
    protected Gwent juego;

    public Fase(Gwent juego) {
        this.juego = juego;
    }

    public abstract void iniciarFase();


}
