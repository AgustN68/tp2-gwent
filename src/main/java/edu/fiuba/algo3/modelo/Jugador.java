package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Carta;

import java.util.ArrayList;

public class Jugador {

    private Tablero tablero;
    private Mazo mazo;
    private Mano mano;
    private PilaDeDescarte pilaDeDescarte;

    public Jugador(Tablero tablero, Mazo mazo) {
        this.tablero = tablero;
        this.mazo = mazo;
        this.mano = new Mano(new ArrayList<>());
        this.pilaDeDescarte = new PilaDeDescarte();
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
        carta.usar(tablero);
    }

    public Puntaje obtenerPuntaje() {
        return tablero.calcularPuntaje();
    }
}
