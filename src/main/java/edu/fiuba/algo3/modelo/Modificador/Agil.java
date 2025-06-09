package edu.fiuba.algo3.modelo.Modificador;

import edu.fiuba.algo3.modelo.Seccion.Seccion;

import java.util.List;

public class Agil extends Modificador {
    List<Seccion> secciones;

    public Agil(List<Seccion> secciones) {
        this.secciones = secciones;
    }


    @Override
    public void aplicarModificador() {

    }
}


