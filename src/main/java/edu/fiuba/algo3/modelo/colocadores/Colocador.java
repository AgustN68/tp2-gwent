package edu.fiuba.algo3.modelo.colocadores;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Tablero;

public interface Colocador {

    void colocar(Unidad unidad, Tablero tablero);
}
