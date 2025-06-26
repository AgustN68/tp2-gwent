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
        /*
        // Crear secciones para los tableros
        List<Seccion> seccionesJ1 = crearSecciones();
        List<Seccion> seccionesJ2 = crearSecciones();

        // Crear tableros
        Tablero tableroJ1 = new Tablero(seccionesJ1);
        Tablero tableroJ2 = new Tablero(seccionesJ2);

        // Crear jugadores
        this.jugador1 = new Jugador(tableroJ1, new PilaDeDescarte());
        this.jugador2 = new Jugador(tableroJ2, new PilaDeDescarte());

        // Establecer nombres
        this.jugador1.setNombre(nombreJugador1);
        this.jugador2.setNombre(nombreJugador2);

        // Cargar los mazos
        cargarMazos();

        // Crear lista de jugadores y establecerla en el juego
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        this.juego.setJugadores(jugadores);

         */
    }

    /**
     * Carga los mazos para ambos jugadores desde el archivo JSON
     */
    /*private void cargarMazos() {
        Lector lector = new LectorJson();
        List<Mazo> mazos = lector.leerMazos(RUTA_JSON, jugador1, jugador2);

        if (mazos != null && mazos.size() >= 2) {
            jugador1.setMazo(mazos.get(0));
            jugador2.setMazo(mazos.get(1));
        }
    }

    /**
     * Crea las secciones necesarias para un tablero (Cuerpo a Cuerpo, Rango, Asedio)

    private List<Seccion> crearSecciones() {
        List<Seccion> secciones = new ArrayList<>();
        secciones.add(new CuerpoACuerpo());
        secciones.add(new Rango());
        secciones.add(new Asedio());
        return secciones;
    }

    public void iniciarFasePreparacion() {
        // Repartir 10 cartas a cada jugador
        jugador1.tomarCartasMazo(10);
        jugador2.tomarCartasMazo(10);

        // Por defecto, se elige aleatoriamente quién va primero
        this.jugadorActual = tirarMoneda() ? jugador1 : jugador2;

        // Resetear estados de pase para la ronda
        jugadorPaso = false;
        rivalPaso = false;
    }

    public boolean tirarMoneda() {
        Random random = new Random();
        return random.nextBoolean();
    }
*/
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
    }

    /*private void cambiarTurno() {
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
    }
*/
    private void finalizarRonda() {
        // Determinar ganador de la ronda
        int puntajeJ1 = jugador1.obtenerPuntaje().obtenerValor();
        int puntajeJ2 = jugador2.obtenerPuntaje().obtenerValor();

        if (puntajeJ1 > puntajeJ2) {
            jugador1.aumentarRondasGanadas();
        } else if (puntajeJ2 > puntajeJ1) {
            jugador2.aumentarRondasGanadas();
        } else {
            // En caso de empate, ambos ganan
            jugador1.aumentarRondasGanadas();
            jugador2.aumentarRondasGanadas();
        }

        // Verificar si alguien ganó el juego
        if (jugador1.rondasGanadas() >= 2 || jugador2.rondasGanadas() >= 2) {
            finalizarJuego();
        } else {
            // Preparar la siguiente ronda
            prepararSiguienteRonda();
        }
    }

    private void prepararSiguienteRonda() {
        // Incrementar contador de rondas
        rondaActual++;

        // Resetear estados de pase
        jugadorPaso = false;
        rivalPaso = false;

        // El perdedor de la ronda anterior comienza
        if (jugador1.obtenerPuntaje().obtenerValor() < jugador2.obtenerPuntaje().obtenerValor()) {
           // jugadorActual = jugador1;
        } else {
          //  jugadorActual = jugador2;
        }
    }

    private void finalizarJuego() {
        // Determinar el ganador final
        String nombreGanador;
        if (jugador1.rondasGanadas() > jugador2.rondasGanadas()) {
            nombreGanador = jugador1.getNombre();
        } else if (jugador2.rondasGanadas() > jugador1.rondasGanadas()) {
            nombreGanador = jugador2.getNombre();
        } else {
            // En caso de empate (no debería ocurrir con 2 rondas ganadas)
            Puntaje puntajeJ1 = jugador1.obtenerPuntaje();
            Puntaje puntajeJ2 = jugador2.obtenerPuntaje();

            if (puntajeJ1.obtenerValor() > puntajeJ2.obtenerValor()) {
                nombreGanador = jugador1.getNombre();
            } else {
                nombreGanador = jugador2.getNombre();
            }
        }

        // Mostrar la pantalla final con el ganador
        if (app != null) {
            app.mostrarPantallaFinal(nombreGanador);
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
