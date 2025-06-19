package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Carta;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private Tablero tablero;
    private Mazo mazo;
    private Mano mano;
    private PilaDeDescarte pilaDeDescarte;

    public Jugador(Tablero tablero, Mazo mazo, PilaDeDescarte pilaDeDescarte) {
        this.tablero = tablero;
        this.mazo = mazo;
        this.mano = new Mano(new ArrayList<>());
        this.pilaDeDescarte = pilaDeDescarte;
    }

    public void validarCartasSuficientesMano(int cantidadCartas) {
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
        this.pilaDeDescarte.descartarUna(carta);
        carta.usar();
    }

    public Puntaje obtenerPuntaje() {
        return tablero.calcularPuntaje();
    }

    public List<Carta> verMano() {
        return mano.obtenerCartas();
    }

    public Carta agarrarDescarte(){
        return this.pilaDeDescarte.agarrarCarta();
    }

    public void tomarCartasEspia() {
        int cantidadCartas = (mazo.obtenerCantidadDeCartas() > 1) ? 2 : 1;
        tomarCartasMazo(cantidadCartas);
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }
}

