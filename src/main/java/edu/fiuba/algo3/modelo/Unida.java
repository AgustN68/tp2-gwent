package edu.fiuba.algo3.modelo;

public class Unida extends Modificador {
    private int cantidadEnSeccion;

    public Unida(int cantidadEnSeccion) {
        this.cantidadEnSeccion = cantidadEnSeccion;
    } // provisorio - Si ya habia una carta en alguna sección, tengo que saber la cantidad inicial
      // previo a aplicar el puntaje

    public void sumarCantidadEnSeccion() {
        this.cantidadEnSeccion++; // se pone una carta, tengo que actualizar
    }


    @Override
    public Puntaje aplicarModificador(Puntaje puntaje) {
        return new Puntaje(puntaje.obtenerValor() * cantidadEnSeccion);
    }
}
