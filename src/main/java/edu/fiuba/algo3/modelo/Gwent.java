package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Fases.Fase;
import edu.fiuba.algo3.modelo.Fases.Final;
import edu.fiuba.algo3.modelo.Fases.Inicial;
import edu.fiuba.algo3.modelo.Fases.Preparacion;

import java.util.List;

public class Gwent {
    private Fase faseActual;
    private List<Jugador> jugadores;

    public Gwent() {
        faseActual = new Inicial(this);
    }

    public void iniciarJuego() {
        faseActual.iniciarFase();
    }

    public void cambiarFase(Fase nuevaFase) {
        faseActual = nuevaFase;
        faseActual.iniciarFase();
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }
}
