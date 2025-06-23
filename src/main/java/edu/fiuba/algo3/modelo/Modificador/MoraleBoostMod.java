package edu.fiuba.algo3.modelo.Modificador;

public class MoraleBoostMod extends Modificador {
    private static final int SUMA = 1;

    @Override
    public void aplicarModificador() {
        unidad.sumaPuntaje(SUMA);
    }
}
