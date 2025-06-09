package edu.fiuba.algo3.modelo.Carta.Especial;

import edu.fiuba.algo3.modelo.Seccion.Seccion;

public class MoraleBoost extends Especial {

    private int producto = 2;

    public void usar(Seccion seccion) {
        seccion.multiplicarPuntaje(producto);
    }
}
