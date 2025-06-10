package edu.fiuba.algo3.modelo.Modificador;

import edu.fiuba.algo3.modelo.Puntaje;

public class Unida extends Modificador {
    private int cantidadEnSeccion;

    public Unida() {
        this.cantidadEnSeccion = 0;
    }

    public void sumarCantidadEnSeccion() {
        this.cantidadEnSeccion++;
    }


    @Override
    public Puntaje aplicarModificador(Puntaje puntaje) {
        return new Puntaje(puntaje.obtenerValor() * cantidadEnSeccion);
    }

    @Override
    public void aplicarModificador() {

    }
}
