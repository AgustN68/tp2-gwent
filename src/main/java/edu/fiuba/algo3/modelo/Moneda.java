package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Moneda {

    private final Random random;

    public Moneda() {
        this.random = new Random();
    }
    public List<Jugador> elegirOrdenJugadores(List<Jugador> jugadores) {
        if (random.nextBoolean()) {
            return jugadores;
        }
        else {
            Jugador primero = jugadores.get(0);
            jugadores.set(0, jugadores.get(1));
            jugadores.set(1, primero);
            return jugadores;
        }
    }
}
