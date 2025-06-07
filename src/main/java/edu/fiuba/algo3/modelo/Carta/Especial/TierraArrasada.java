package edu.fiuba.algo3.modelo.Carta.Especial;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Tablero;

public class TierraArrasada extends Especial {

    private Tablero tablero;

    public TierraArrasada(Tablero tablero) {
        this.tablero = tablero;
    }

    @Override
    public void usar() {
        tablero.removerCartaMasFuerte();
    }

}
