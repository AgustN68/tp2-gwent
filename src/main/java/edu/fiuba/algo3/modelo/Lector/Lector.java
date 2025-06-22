package edu.fiuba.algo3.modelo.Lector;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mazo;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Tablero;

import java.util.List;

public interface Lector {

    public List<Mazo> leerMazos(String ruta, Jugador jugador1, Jugador jugador2);
}
