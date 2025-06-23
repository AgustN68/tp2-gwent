package edu.fiuba.algo3.modelo;

import java.util.List;

public class Ronda {

    private List<Jugador> jugadoresEnRonda;
    private List<Jugador> jugadores;


    public Ronda(List<Jugador> jugadores) {
        jugadoresEnRonda = jugadores;
        this.jugadores = jugadores;
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
        Jugador ganador = jugadores.get(0);
        for (Jugador jugador : jugadores) {
            Puntaje puntajeActual = jugador.obtenerPuntaje();
            if (puntajeActual.esMayor(ganador.obtenerPuntaje())) {
                ganador = jugador;
            }
        }
        aumentarGanadorRonda(ganador);
    }

    private boolean rondaTerminada() {
        return jugadoresEnRonda.isEmpty();
    }

    private void aumentarGanadorRonda(Jugador ganadorRonda) {
        ganadorRonda.aumentarRondasGanadas();
    }

    public void pasar(Jugador jugador) {
        jugadoresEnRonda.remove(jugador);
    }
}
