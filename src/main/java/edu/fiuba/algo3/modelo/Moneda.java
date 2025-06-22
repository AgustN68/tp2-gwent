package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Moneda {

    private final Random random;

    public Moneda() {
        this.random = new Random();
    }
    public List<Jugador> elegirOrdenJugadores(Jugador jugador1, Jugador jugador2) {
        List<Jugador> jugadores = new ArrayList<Jugador>();
        if (random.nextBoolean()) {
            jugadores.add(jugador1);
            jugadores.add(jugador2);
            return jugadores;
        }
        else {
            jugadores.add(jugador2);
            jugadores.add(jugador1);
            return jugadores;
        }
    }
}
