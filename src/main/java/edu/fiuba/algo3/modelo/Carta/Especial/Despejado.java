package edu.fiuba.algo3.modelo.Carta.Especial;

import edu.fiuba.algo3.modelo.Tablero;

public class Despejado extends Clima {

    private Tablero tablero1;
    private Tablero tablero2;

    public Despejado(Tablero tablero1, Tablero tablero2) {
        super();
        this.tablero1 = tablero1;
        this.tablero2 = tablero2;
    }

    public void usar() {
        tablero1.limpiarClima();
        tablero2.limpiarClima();
    }

}
