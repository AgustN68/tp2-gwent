package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Seccion.Seccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Jugador {

    private Tablero tablero;
    private Mazo mazo;
    private Mano mano;
    private PilaDeDescarte pilaDeDescarte;
    private String nombre;
    private int rondasGanadas;

    public Jugador(Tablero tablero, PilaDeDescarte pilaDeDescarte) {
        Objects.requireNonNull(tablero, "El tablero no puede ser null");
        Objects.requireNonNull(pilaDeDescarte, "La pila de descarte no puede ser null");
        this.tablero = tablero;
        this.mano = new Mano(new ArrayList<>());
        this.pilaDeDescarte = pilaDeDescarte;
        rondasGanadas = 0;
        this.nombre = "";
    }

    public void validarCartasSuficientesMano(int cantidadCartas) {
        if (cantidadCartas < 0) {
            throw new IllegalArgumentException("La cantidad de cartas no puede ser negativa");
        }
        mano.tieneCartasSuficientes(cantidadCartas);
    }

    public void tomarCartasMazo(int cantidadCartas) {
        for (int i = 0; i < cantidadCartas; i++) {
            mano.tomarCarta(mazo);
        }
    }

    public Carta seleccionarCarta(int posicion) {
        return mano.agarrarCarta(posicion);
    }

    public void usarCarta(int posicion) {
        Carta carta = mano.agarrarCarta(posicion);
        carta.usar();
        pilaDeDescarte.descartarUna(carta);
    }

    public void usarCarta(int posicion, Seccion seccion) {
        Carta carta = mano.agarrarCarta(posicion);
        carta.usar(seccion);
        pilaDeDescarte.descartarUna(carta);
    }

    public void descartarCarta(int posicion) {
        this.pilaDeDescarte.descartarUna(mano.agarrarCarta(posicion));
    }

    public Puntaje obtenerPuntaje() {
        return tablero.calcularPuntaje();
    }

    public List<Carta> verMano() {
        return mano.obtenerCartas();
    }

    public Carta agarrarDescarte() {
        return this.pilaDeDescarte.agarrarCarta();
    }

    public void tomarCartasEspia() {
        Objects.requireNonNull(mazo, "El mazo no ha sido asignado");
        int cantidadCartas = (mazo.obtenerCantidadDeCartas() > 1) ? 2 : 1;
        tomarCartasMazo(cantidadCartas);
    }

    public void setMazo(Mazo mazo) {
        Objects.requireNonNull(mazo, "El mazo no puede ser null");
        this.mazo = mazo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser null");
        this.nombre = nombre;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void aumentarRondasGanadas() {
        rondasGanadas++;
    }

    public int rondasGanadas() {
        return rondasGanadas;
    }

    public void limpiarTablero() {
        List<Carta> cartasUsadas = tablero.limpiarTablero();
        pilaDeDescarte.descartarCartas(cartasUsadas);
    }
}
