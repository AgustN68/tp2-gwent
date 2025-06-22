package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.Gwent;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ronda;

import java.util.ArrayList;
import java.util.List;

public class Juego extends Fase{

    Jugador primerJugador;
    Jugador segundoJugador;
    private static final int CANT_RONDAS = 3;
    List<Ronda> rondas;

    public Juego(Gwent juego, Jugador primerJugador, Jugador segundoJugador) {
        super(juego);
        this.primerJugador = primerJugador;
        this.segundoJugador = segundoJugador;
        seleccionarRondas();
    }

    private void seleccionarRondas() {
        rondas = new ArrayList<Ronda>();
        for (int i = 0; i < CANT_RONDAS; i++) {
            rondas.add(new Ronda());
        }
    }

    @Override
    public void iniciarFase() {
        for (Ronda ronda : rondas) {
            ronda.jugarRonda();
        }

        juego.cambiarFase(new Final(juego));
    }
}
