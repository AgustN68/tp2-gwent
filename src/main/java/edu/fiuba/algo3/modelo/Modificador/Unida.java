package edu.fiuba.algo3.modelo.Modificador;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Puntaje;

public class Unida extends Modificador {

    @Override
    public Puntaje aplicarModificador(Puntaje puntaje) {
        return unidad.actualizarPuntajeSegunCantMismoTipoEnSeccion(puntaje);
    }

    @Override
    public void aplicarModificador() {

    }
}
