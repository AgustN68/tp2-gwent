package edu.fiuba.algo3.modelo.Modificador;

public class MoraleBoostMod extends Modificador {
    private static final int SUMA = 1;
    private String descripcion = "Morale Boost: Añaden +1 de fuerza a todas las unidades en la fila en la que se juegan, excluyéndose a sí mismas";

    @Override
    public void aplicarModificador() {
        unidad.sumaPuntaje(SUMA);
    }
    @Override
    public String obtenerDescripcion() {
        return this.descripcion;
    }
}
