package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.colocadores.Colocador;
import edu.fiuba.algo3.modelo.Modificador;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Tablero;

public class Unidad extends Carta {

    private Puntaje puntaje;
    private Modificador modificador;
    private Colocador colocador;

    public Unidad(Colocador colocador, int puntosIniciales, Modificador modificador) {
        this.colocador = colocador;
        puntaje = new Puntaje(puntosIniciales);
        this.modificador = modificador;
    }

    @Override
    public void usar(Tablero tablero){
        colocador.colocar(this, tablero);
    }

    public Puntaje calcularPuntaje(){
        return this.modificador.aplicarModificador(puntaje);
    }

    public void reducirPuntaje(int valor){
        this.puntaje.reducirPuntajeA(valor);
    }
}
