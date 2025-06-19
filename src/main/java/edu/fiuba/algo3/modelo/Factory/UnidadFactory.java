package edu.fiuba.algo3.modelo.Factory;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.Modificador;
import edu.fiuba.algo3.modelo.Seccion.Seccion;

public class UnidadFactory {

    public static Unidad crearUnidad(String nombre, Long puntos, Seccion seccion, Modificador modificador) {
        return new Unidad(nombre, seccion,puntos.intValue(), modificador);
    }
}
