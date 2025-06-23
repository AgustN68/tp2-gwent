package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.Gwent;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ronda;

import java.util.ArrayList;
import java.util.List;

public class Juego extends Fase{

    List<Ronda> rondas = new ArrayList<Ronda>();
    private Boolean juegoTerminado;
    Jugador ganador;
    protected static final int CANT_RONDAS_NECESARIAS = 2;

    public Juego(Gwent juego) {
        super(juego);
        this.juegoTerminado = false;
    }

    @Override
    public void iniciarFase() {
        while (!juegoTerminado) {
            Ronda rondaActual = new Ronda(jugadores);
            rondas.add(rondaActual);
            rondaActual.jugarRonda();
            juegoTerminado = comprobarGanador();
        }
        siguienteFase();
    }

    private void siguienteFase() {
        juego.cambiarFase(new Final(juego, ganador));
    }

    private Boolean comprobarGanador() {
        for (Jugador jugador : jugadores) {
            if (jugador.rondasGanadas() >= CANT_RONDAS_NECESARIAS) {
                ganador = jugador;
                return true;
            }
        }
        return false;
    }


}
