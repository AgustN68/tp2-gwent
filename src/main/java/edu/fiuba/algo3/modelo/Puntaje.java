package edu.fiuba.algo3.modelo;

public class Puntaje {

    private int puntaje;

    public Puntaje(int puntosIniciales) {
        this.puntaje = puntosIniciales;
    }

    public Boolean equals(int puntaje) {
        return puntaje == this.puntaje;
    }
}
