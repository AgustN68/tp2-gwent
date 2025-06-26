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

        Jugador jugador1 = juego.getJugador1();
        Jugador jugador2 = juego.getJugador2();

        // Preparar jugadores (repartir cartas, etc.)
        prepararJugadores(jugador1,jugador2);

        // Determinar el orden de los jugadores usando la moneda
        juego.setOrdenJugadores(moneda.elegirOrdenJugadores(jugador1, jugador2));
        
    }

    private void prepararJugadores(Jugador jugador1, Jugador jugador2) {
        jugador1.tomarCartasMazo(CANT_CARTAS_MANO);
        jugador2.tomarCartasMazo(CANT_CARTAS_MANO);
    }




}
