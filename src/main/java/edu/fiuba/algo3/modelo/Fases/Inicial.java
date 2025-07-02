package edu.fiuba.algo3.modelo.Fases;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima.Clima;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima.Despejado;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima.SinClima;
import edu.fiuba.algo3.modelo.Lector.Lector;
import edu.fiuba.algo3.modelo.Lector.LectorJson;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;

import java.util.ArrayList;
import java.util.List;

public class Inicial extends Fase {

    private static final String RUTA_JSON = "src/main/java/edu/fiuba/algo3/archivos/gwent.json";
    private String nombreJugador1;
    private String nombreJugador2;
    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero1;
    private Tablero tablero2;

    private Lector lector = new LectorJson();

    public Inicial(Gwent juego, String nombreJugador1, String nombreJugador2) {
        super(juego);
        this.nombreJugador1 = nombreJugador1;
        this.nombreJugador2 = nombreJugador2;
    }


    @Override
    public void iniciarFase() {
        prepararJugadores();
        juego.setJugadores(jugador1, jugador2);
        juego.cambiarFase(new Preparacion(juego));
    }

    private void prepararJugadores() {
        crearTableros();
        crearJugadores();

        prepararSecciones();

        List <Mazo> mazos = lector.leerMazos(RUTA_JSON, jugador1, jugador2);

        jugador1.setMazo(mazos.get(0));
        jugador2.setMazo(mazos.get(1));
    }

    private void crearTableros() {
        tablero1 = crearTablero();
        tablero2 = crearTablero();
    }

    private void prepararSecciones() {

        List<Seccion> seccionesT1 = tablero1.getSecciones();
        List<Seccion> seccionesT2 = tablero2.getSecciones();

        for (Seccion seccion : seccionesT1) {
            seccion.establecerClima(new SinClima());
        }
        for (Seccion seccion : seccionesT2) {
            seccion.establecerClima(new SinClima());
        }
    }

    private void crearJugadores() {
        jugador1 = new Jugador(tablero1,new PilaDeDescarte());
        jugador1.setNombre(nombreJugador1);
        jugador2 = new Jugador(tablero2, new PilaDeDescarte());
        jugador2.setNombre(nombreJugador2);
    }

    private Tablero crearTablero() {
        List<Seccion> secciones = new ArrayList<>();
        secciones.add(new CuerpoACuerpo());
        secciones.add(new Rango());
        secciones.add(new Asedio());

        Tablero tablero = new Tablero(secciones);

        return tablero;

    }

    /**
     * Establece la lista de jugadores para esta fase
     * @param jugadores Lista de jugadores a establecer
     */
   /* public void setJugadores() {
        this.jugadores = jugadores;
    }*/
}
