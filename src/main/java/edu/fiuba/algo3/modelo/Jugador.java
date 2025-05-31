package edu.fiuba.algo3.modelo;

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
    }

    public void validarCartasSuficientesMazo(int cantidadUnidades, int cantidadEspeciales) {
        mazo.tieneCartasSuficientes(cantidadUnidades, cantidadEspeciales);
    }

    public void validarCartasSuficientesMano(int cantidadCartas) {
        mano.tieneCartasSuficientes(cantidadCartas);
    }

    public void tomarCartasMazo(int cantidadCartas) {
        for (int i = 0; i < cantidadCartas; i++) {
            mano.tomarCarta(mazo);
        }
    }
}
