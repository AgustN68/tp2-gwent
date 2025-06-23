package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.Gwent;
import edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;

public abstract class Fase {
    protected List<Jugador> jugadores;
    protected Gwent juego;

    public Fase(Gwent juego) {
        this.juego = juego;
    }

    public abstract void iniciarFase();

}
