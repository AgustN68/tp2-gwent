package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Modificador;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion;
import edu.fiuba.algo3.modelo.Tablero;

public class Unidad extends Carta {

    private Puntaje puntaje;
    private Seccion seccion;
    private Modificador modificador;

    public Unidad(Seccion seccion, int puntosIniciales, Modificador modificador) {
        this.seccion = seccion;
        puntaje = new Puntaje(puntosIniciales);
        this.modificador = modificador;
    }

    @Override
    public void usar(Tablero tablero){
        this.ubicar(tablero);

    }
    public void ubicar(Tablero tablero){
        tablero.ubicar(this, seccion);
    }

    public Puntaje calcularPuntaje(){
        return this.modificador.aplicarModificador(puntaje);
    }
}
