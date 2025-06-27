package edu.fiuba.algo3.modelo.Modificador;

import edu.fiuba.algo3.modelo.Puntaje;

public class Legendaria extends Modificador {

    private String descripcion = "Legendaria: No se ven afectadas por cartas especiales, cartas de clima ni habilidades.";

    @Override
    public void aplicarModificador() {
    }

    @Override
    public void aplicarEfectoClima(Puntaje puntaje, int valor) {
        puntaje.modificarValor(puntaje.obtenerValor()); // para que no quede anemica
    }

    @Override
    public Boolean esVulnerableAEspeciales() {
        return false;
    }
    @Override
    public String obtenerDescripcion() {
        return this.descripcion;
    }
}