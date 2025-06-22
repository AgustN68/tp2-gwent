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
        prepararJugador(jugador1);
        prepararJugador(jugador2);

        List<Jugador> jugadores = moneda.elegirOrdenJugadores(jugador1,jugador2);

        juego.cambiarFase(new Juego(juego, jugadores.get(0), jugadores.get(1)));

    }

    private void prepararJugador(Jugador jugador) {
        jugador.tomarCartasMazo(CANT_CARTAS_MANO);
        descarcarCartas(jugador);
    }

    private void descarcarCartas(Jugador jugador) {
        // mas adelante
    }



}
