package edu.fiuba.algo3.modelo;

public class Unidad extends Carta{

    private Puntaje puntaje;
    private Seccion seccion;
    private Modificador modificador;

    public Unidad(Seccion seccion, int puntosIniciales){
        this.seccion = seccion;
        puntaje = new Puntaje(puntosIniciales);
    }

    public void ubicar(Tablero tablero){
        tablero.ubicar(this);
    }

    public Puntaje puntaje(){
        return this.modificador.aplicarModificador(puntaje);
    }
}
