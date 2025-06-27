package edu.fiuba.algo3.modelo.Carta.Especial;

import edu.fiuba.algo3.modelo.Seccion.Seccion;

import java.util.List;

public class MoraleBoost extends Especial {

    private int producto = 2;

    @Override
    public void usar(Seccion seccion) {
        seccion.multiplicarPuntaje(producto);
    }

    @Override
    public void usar() {

    }

    @Override
    public void usar(List<Seccion> secciones) {
    }
}
