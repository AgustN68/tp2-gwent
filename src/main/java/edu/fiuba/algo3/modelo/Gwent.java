package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Fases.*;

import java.util.List;

public class Gwent {
    private Fase faseActual;
    private Jugador jugador1;
    private Jugador jugador2;
    private List<Jugador> jugadoresOrdenados;
    private Juego faseJuego;
    private Jugador ganador;

    public Gwent() {
    }

    public void iniciarJuego(String nombreJugador1, String nombreJugador2) {
        faseActual = new Inicial(this, nombreJugador1, nombreJugador2);
        faseActual.iniciarFase();
    }

    public void cambiarFase(Fase nuevaFase) {
        faseActual = nuevaFase;
        faseActual.iniciarFase();
    }

    public void setJugadores(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    public Jugador getJugador1() {
        return jugador1;
    }
    public Jugador getJugador2() {
        return jugador2;
    }

    public void iniciarFaseJuego() {
        faseJuego = new Juego(this, jugadoresOrdenados);
        cambiarFase(faseJuego);
    }

    public void setOrdenJugadores(List<Jugador> jugadores) {
        this.jugadoresOrdenados = jugadores;
    }

    public Jugador jugadorActual() {
        return faseJuego.getJugadorActual();
    }

    public void jugarCarta(int posicionCarta) {
        faseJuego.jugarCarta(posicionCarta);
    }

    public void pasarTurno() {
        faseJuego.pasarTurno();
    }

    public void setGanador(Jugador jugador) {
        ganador = jugador;
    }

    // Falta implementacion
    public void limpiarTablero() {
    }
}
