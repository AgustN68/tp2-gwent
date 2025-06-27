package edu.fiuba.algo3.modelo.Modificador;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Puntaje;

public class Unida extends Modificador {

    private String descripcion = "Unida: Al colocarse junto a una carta con el mismo nombre, duplican la fuerza de ambas (o más)";

    @Override
    public Puntaje aplicarModificador(Puntaje puntaje) {
        return unidad.actualizarPuntajeSegunCantMismoTipoEnSeccion(puntaje);
    }

    @Override
    public void aplicarModificador() {

    }

    @Override
    public String obtenerDescripcion() {
        return this.descripcion;
    }
}
