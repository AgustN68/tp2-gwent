package edu.fiuba.algo3.modelo;

import java.util.List;

public class Ronda {

    private List<Jugador> jugadoresEnRonda;
    private Jugador jugadorActual;
    private Jugador jugador1;
    private Jugador jugador2;


    public Ronda(List<Jugador> jugadores) {
        jugadorActual = jugadores.get(0);
        jugador1 = jugadores.get(0);
        jugador2 = jugadores.get(1);
        jugadoresEnRonda = jugadores;
    }

    public void jugarRonda() {
        while (!rondaTerminada()) {
            for (Jugador jugador : jugadoresEnRonda) {
                Turno turnoJugador = new Turno(jugador, this);
                turnoJugador.jugarTurno();
            }
        }

        finalizarRonda();
    }

    private void finalizarRonda() {
        Puntaje puntajeJ1 = jugador1.obtenerPuntaje();
        Puntaje puntajeJ2 = jugador2.obtenerPuntaje();
        if (puntajeJ1.esMayor(puntajeJ2)) {
            aumentarGanadorRonda(jugador1);
        }
        else if (puntajeJ2.esMayor(puntajeJ1)) {
            aumentarGanadorRonda(jugador2);
        }
        else {
            aumentarGanadorRonda(jugador1);
            aumentarGanadorRonda(jugador2);
        }
    }

    private boolean rondaTerminada() {
        return jugadoresEnRonda.isEmpty();
    }

    private void aumentarGanadorRonda(Jugador ganadorRonda) {
        ganadorRonda.aumentarRondasGanadas();
    }

    public void pasar(Jugador jugador) {
        cambiarTurno();
        jugadoresEnRonda.remove(jugador);
    }

    public Boolean rondaFinalizada() {
        if (jugadoresEnRonda.isEmpty()) {
            finalizarRonda();
            return true;
        }
        return false;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public void jugarCarta(int posicionCarta) {
        if (!jugadorActual.verMano().isEmpty() && posicionCarta >= 0 && posicionCarta < jugadorActual.verMano().size()) {
            jugadorActual.usarCarta(posicionCarta);
            cambiarTurno();
        }
    }

    private void cambiarTurno() {
        if (jugadoresEnRonda.size() > 1) {
            if (jugadorActual.equals(jugadoresEnRonda.get(0))){
                jugadorActual = jugadoresEnRonda.get(1);
            }
            else {
                jugadorActual = jugadoresEnRonda.get(0);
            }
        }
    }

    public void pasarTurno() {
        pasar(jugadorActual);
    }
}
