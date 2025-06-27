package edu.fiuba.algo3.modelo.Modificador;

public class SinModificador extends Modificador{

    private String descripcion = "";
    @Override
    public void aplicarModificador() {
    }
    @Override
    public String obtenerDescripcion() {
        return this.descripcion;
    }
}

