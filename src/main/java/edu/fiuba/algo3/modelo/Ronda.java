package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Especial.Clima.Clima;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima.Despejado;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasSuficientesException;

import edu.fiuba.algo3.modelo.Seccion.Seccion;

import java.util.List;

public class Ronda {

    private List<Jugador> jugadoresEnRonda;
    private Jugador jugadorActual;
    private Jugador jugador1;
    private Jugador jugador2;


    public Ronda(List<Jugador> jugadores) {
        if (jugadores.size() != 2) {
            throw new IllegalArgumentException("La ronda debe tener exactamente dos jugadores");
        }
        jugadorActual = jugadores.get(0);
        jugador1 = jugadores.get(0);
        jugador2 = jugadores.get(1);
        jugadoresEnRonda = jugadores;

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
        if (jugadorActual.verMano().isEmpty()) {
            throw new NoHayCartasSuficientesException("La mano del jugador está vacía");
        }
        if (posicionCarta < 0 || posicionCarta >= jugadorActual.verMano().size()) {
            throw new IndexOutOfBoundsException("Posición inválida");
        }
        jugadorActual.usarCarta(posicionCarta);
        cambiarTurno();
    }

    public void jugarCarta(int posicionCarta, Seccion seccion) {
        if (!jugadorActual.verMano().isEmpty() && posicionCarta >= 0 && posicionCarta < jugadorActual.verMano().size()) {
            jugadorActual.usarCarta(posicionCarta, seccion);
            cambiarTurno();
        }
    }

    public void jugarCarta(int posicionCarta, List<Seccion> secciones) {
        if (!jugadorActual.verMano().isEmpty() && posicionCarta >= 0 && posicionCarta < jugadorActual.verMano().size()) {
            jugadorActual.usarCarta(posicionCarta, secciones);
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
