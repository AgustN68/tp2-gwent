package edu.fiuba.algo3.modelo.Modificador;

import edu.fiuba.algo3.modelo.Puntaje;

public class Legendaria extends Modificador {
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
}