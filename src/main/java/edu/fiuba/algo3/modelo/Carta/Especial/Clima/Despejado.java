package edu.fiuba.algo3.modelo.Carta.Especial.Clima;

import edu.fiuba.algo3.modelo.Seccion.Seccion;

import java.util.List;

public class Despejado extends Clima {

    public Despejado(List<Seccion> secciones) {
        super(secciones);
    }

    @Override
    public void usar() {
        for (Seccion seccion : seccionesAfectadas) {
            seccion.restaurarPuntaje();
        }
    }

    @Override
    public void usar(Seccion seccion) {

    }
}
