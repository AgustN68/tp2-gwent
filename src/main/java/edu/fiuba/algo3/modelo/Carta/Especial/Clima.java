package edu.fiuba.algo3.modelo.Carta.Especial;

import edu.fiuba.algo3.modelo.Seccion.Seccion;

import java.util.List;

public class Clima extends Especial {

    protected static final int VALOR = 1;

    protected List<Seccion> seccionesAfectadas;

    public Clima(List<Seccion> secciones) {
        seccionesAfectadas = secciones;
    }

    public Clima() {

    }

    public void usar() {
        for (Seccion seccion : seccionesAfectadas) {
            seccion.reducirPuntaje(VALOR);
        }

    }

}
