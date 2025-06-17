package edu.fiuba.algo3.modelo.Modificador;

import edu.fiuba.algo3.modelo.Puntaje;

public abstract class Modificador {

    public Puntaje aplicarModificador(Puntaje puntaje) {
        return puntaje;
    }

    public abstract void aplicarModificador();

    public void aplicarEfectoClima(Puntaje puntaje, int valor) {
        puntaje.modificarValor(valor);
    }

    public Boolean esVulnerableAEspeciales() {
        return true;
    }

    public void aplicarMultiplicacion(Puntaje puntaje, int multiplicador) {
        if (esVulnerableAEspeciales()) {
            puntaje.multiplicarValor(multiplicador);
        }
    }
}
