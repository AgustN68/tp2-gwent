package edu.fiuba.algo3.modelo;

public class Unidad extends Carta{

    private Puntaje puntaje;
    private Seccion seccion;
    private Modificador modificador;

    public Unidad(Seccion seccion, int puntosIniciales, Modificador modificador) {
        this.seccion = seccion;
        puntaje = new Puntaje(puntosIniciales);
        this.modificador = modificador;
    }

    public void ubicar(Tablero tablero){
        tablero.ubicar(this, seccion);
    }

    public Puntaje calcularPuntaje(){
        return this.modificador.aplicarModificador(puntaje);
    }
}
