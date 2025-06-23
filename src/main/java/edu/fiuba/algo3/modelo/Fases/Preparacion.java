package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.Gwent;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Moneda;

import java.util.List;

public class Preparacion extends Fase {

    private Moneda moneda = new Moneda();
    private static final int CANT_CARTAS_MANO = 10;

    public Preparacion(Gwent juego) {
        super(juego);
    }


    @Override
    public void iniciarFase() {
        prepararJugadores(jugadores);

        List<Jugador> jugadores = moneda.elegirOrdenJugadores(this.jugadores);

        juego.cambiarFase(new Juego(juego));

    }

    private void prepararJugadores(List<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            jugador.tomarCartasMazo(CANT_CARTAS_MANO);
            descarcarCartas(jugador);
        }

    }

    private void descarcarCartas(Jugador jugador) {
        // mas adelante
    }



}
