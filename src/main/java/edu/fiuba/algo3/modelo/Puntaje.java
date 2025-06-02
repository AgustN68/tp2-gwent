package edu.fiuba.algo3.modelo;

public class Puntaje {

    private int valor;

    public Puntaje(int puntosIniciales) {
        this.valor = puntosIniciales;
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

    public void reducirPuntajeA(int valor) {
        this.valor = valor;
    }
}
