package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Gwent;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Lector.Lector;
import edu.fiuba.algo3.modelo.Lector.LectorJson;
import edu.fiuba.algo3.modelo.Mazo;
import edu.fiuba.algo3.modelo.PilaDeDescarte;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.vistas.GwentApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GwentController {
    private Gwent juego;
    private Jugador jugador1;
    private Jugador jugador2;
    //private Jugador jugadorActual;
    private boolean jugadorPaso;
    private boolean rivalPaso;
    private int rondaActual;
    private GwentApp app;
    private static final String RUTA_JSON = "src/main/java/edu/fiuba/algo3/archivos/gwent.json";

    public GwentController() {
        this.juego = new Gwent();
        this.rondaActual = 1;
    }

    public void setApp(GwentApp app) {
        this.app = app;
    }

    /**
     * Inicia el juego creando directamente los jugadores, tableros y mazos.
     * Este método evita usar la fase Inicial del juego para no bloquear la interfaz.
     */
    public void iniciarJuego(String nombreJugador1, String nombreJugador2) {
        juego.iniciarJuego(nombreJugador1, nombreJugador2);
    }

    public void descartarCartas(Jugador jugador, List<Integer> posicionesCartas) {
        // Descarta las cartas seleccionadas y toma nuevas del mazo
        // Nota: las posiciones deben estar ordenadas de mayor a menor para no afectar los índices

        posicionesCartas.sort((a, b) -> b - a);

        for (int posicion : posicionesCartas) {
            jugador.descartarCarta(posicion);
        }

        // Tomar nuevas cartas
        jugador.tomarCartasMazo(posicionesCartas.size());
    }

    public void jugarCarta(int posicionCarta) {
        juego.jugarCarta(posicionCarta);
    }

    public void pasarTurno() {
        juego.pasarTurno();
        if (juego.hayGanador()) {
            finalizarJuego();
        }
    }

    private void finalizarJuego() {
        // Mostrar la pantalla final con el ganador
        if (app != null) {
            app.mostrarPantallaFinal(juego.getGanador());
        }
    }

    // Getters y setters
    public Jugador getJugador1() {
        return juego.getJugador1();
    }

    public Jugador getJugador2() {
        return juego.getJugador2();
    }

    public Jugador getJugadorActual() {
        return juego.jugadorActual();
    }

    public int getRondaActual() {
        return rondaActual;
    }

    public Gwent getJuego() {
        return juego;
    }

    public void iniciarFaseJuego() {
        juego.iniciarFaseJuego();
    }
}
