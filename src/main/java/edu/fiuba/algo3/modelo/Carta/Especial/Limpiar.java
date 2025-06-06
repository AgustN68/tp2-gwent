package edu.fiuba.algo3.modelo.Carta.Especial;

import edu.fiuba.algo3.modelo.Tablero;

public class Limpiar extends Especial {

    private Tablero tablero1;
    private Tablero tablero2;

    public Limpiar(Tablero tablero1, Tablero tablero2) {
        this.tablero1 = tablero1;
        this.tablero2 = tablero2;
    }

    public void usar() {
        tablero1.limpiarClima();
        tablero2.limpiarClima();

    }
}
