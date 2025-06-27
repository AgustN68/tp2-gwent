package edu.fiuba.algo3.modelo;

public class Puntaje {

    private int valor;
    private int valorOriginal;

    public Puntaje(int puntosIniciales) {
        if (puntosIniciales < 0) {
            throw new IllegalArgumentException("El puntaje no puede ser negativo: " + puntosIniciales);
        }
        this.valor = puntosIniciales;
        this.valorOriginal = puntosIniciales;
    }

    public Puntaje sumarPuntaje(Puntaje unPuntaje) {
        return new Puntaje(valor + unPuntaje.obtenerValor());
    }

    public Puntaje restarPuntaje(Puntaje unPuntaje) {
        return new Puntaje(valor - unPuntaje.obtenerValor());
    }

    public Puntaje multiplicarPuntaje(Puntaje unPuntaje) {
        return new Puntaje(valor * unPuntaje.obtenerValor());
    }

    public int obtenerValor() {
        return valor;
    }

    public Boolean equals(int valor) {
        return valor == this.valor;
    }

    public Boolean equalsPuntaje(Puntaje puntaje) {
        return puntaje.obtenerValor() == this.valor;
    }

    public void modificarValor(int valor) {
        this.valor = valor;
    }

    public void reiniciarValor() {
        valor = valorOriginal;
    }

    public void sumarValor(int suma) {
        valor = valor + suma;
    }

    public void multiplicarValor(int multiplicador) {
        valor = valor * multiplicador;
    }

    public boolean esMayor(Puntaje puntaje) {
        return valor > puntaje.obtenerValor();
    }
}
