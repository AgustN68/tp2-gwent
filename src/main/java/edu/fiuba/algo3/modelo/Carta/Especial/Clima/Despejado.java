package edu.fiuba.algo3.modelo.Carta.Especial.Clima;

import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Tablero;

import java.util.List;

public class Despejado extends Especial implements ClimaPosible{

    private Tablero tablero1;
    private Tablero tablero2;

    public Despejado(Tablero tablero1, Tablero tablero2) {
        this.tablero1 = tablero1;
        this.tablero2 = tablero2;
    }


    @Override
    public void usar() {
        tablero1.limpiarClima(this);
        tablero2.limpiarClima(this);
    }

    @Override
    public void usar(Seccion seccion) {

    }

    @Override
    public void usar(List<Seccion> secciones) {

    }

    @Override
    public void modificarPuntaje(List<Unidad> unidades) {
        for (Unidad unidad : unidades) {
            unidad.restaurarPuntaje();
        }
    }
}
