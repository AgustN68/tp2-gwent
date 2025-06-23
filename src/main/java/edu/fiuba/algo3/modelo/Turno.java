package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Carta;

public class Turno {

    private Jugador jugador;
    private Ronda rondaActual;

    public Turno(Jugador jugador, Ronda ronda) {
        this.jugador = jugador;
        rondaActual = ronda;
    }
    public void jugarTurno(){

    }

    public void elegirCarta(){
        Carta carta = jugador.seleccionarCarta(0);
        carta.usar();
    }

    public void pasarRonda(){
        rondaActual.pasar(jugador);
    }
}
