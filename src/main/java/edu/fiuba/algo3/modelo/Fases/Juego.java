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
    private List<Jugador> jugadores;
    private Ronda rondaActual;

    public Juego(Gwent juego, List<Jugador> jugadoresOrdenados) {
        super(juego);
        this.juegoTerminado = false;
        this.jugadores = jugadoresOrdenados;
    }

    @Override
    public void iniciarFase() {
        rondaActual = new Ronda(new ArrayList<>(jugadores));
        rondas.add(rondaActual);
    }

    private void siguienteFase() {
        juego.cambiarFase(new Final(juego, ganador));
    }

    private Boolean hayGanador() {
        for (Jugador jugador : jugadores) {
            if (jugador.rondasGanadas() >= CANT_RONDAS_NECESARIAS) {
                juego.setGanador(jugador);
                return true;
            }
        }
        return false;
    }


    public Jugador getJugadorActual() {
        return rondaActual.getJugadorActual();
    }

    public void jugarCarta(int posicionCarta) {
        rondaActual.jugarCarta(posicionCarta);
    }

    public void pasarTurno() {
        rondaActual.pasarTurno();
        comprobarFinDeRonda();
    }

    private void comprobarFinDeRonda() {
        if (rondaActual.rondaFinalizada()){
            terminarRonda();
        }
    }

    private void terminarRonda() {
        if (!hayGanador()) {
            juego.limpiarTablero();
            rondaActual = new Ronda(new ArrayList<>(jugadores));
        }
    }


}
