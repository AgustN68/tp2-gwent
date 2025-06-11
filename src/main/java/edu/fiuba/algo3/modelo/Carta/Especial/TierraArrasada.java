package edu.fiuba.algo3.modelo.Carta.Especial;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Tablero;

public class TierraArrasada extends Especial {


    public void usar(Tablero tablero) {
        tablero.removerUnidadesMasFuertes();
    }

}
