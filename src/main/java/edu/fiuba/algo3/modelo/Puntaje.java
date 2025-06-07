package edu.fiuba.algo3.modelo;

public class Puntaje {

    private int valor;
    private int valorOriginal;

    public Puntaje(int puntosIniciales) {
        this.valor = puntosIniciales;
        this.valorOriginal = puntosIniciales;
    }

    public Puntaje sumarPuntaje(Puntaje unPuntaje) {
        return new Puntaje(valor + unPuntaje.obtenerValor());
    }

    public Puntaje restarPuntaje(Puntaje unPuntaje) {
        return new Puntaje(valor - unPuntaje.obtenerValor());
    }

    public int obtenerValor() {
        return valor;
    }

    public Boolean equals(int valor) {
        return valor == this.valor;
    }

    public void modificarValor(int valor) {
        this.valor = valor;
    }

    public void reiniciarValor() {
        valor = valorOriginal;
    }
}
