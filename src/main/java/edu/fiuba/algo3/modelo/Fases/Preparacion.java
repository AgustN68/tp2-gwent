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
        // Obtener los jugadores actuales del juego
        this.jugadores = juego.getJugadores();

        // Preparar jugadores (repartir cartas, etc.)
        prepararJugadores(jugadores);

        // Determinar el orden de los jugadores usando la moneda
        List<Jugador> jugadoresOrdenados = moneda.elegirOrdenJugadores(this.jugadores);

        // Actualizar la lista de jugadores en el juego con el nuevo orden
        juego.setJugadores(jugadoresOrdenados);

        // Cambiar a la fase de Juego
        Juego faseJuego = new Juego(juego);
        faseJuego.setJugadores(jugadoresOrdenados); // Pasar explícitamente los jugadores a la siguiente fase
        juego.cambiarFase(faseJuego);
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
