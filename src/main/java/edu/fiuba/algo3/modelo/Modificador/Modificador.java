package edu.fiuba.algo3.modelo.Modificador;

import edu.fiuba.algo3.modelo.Puntaje;

public abstract class Modificador {

    public Puntaje aplicarModificador(Puntaje puntaje) {
        return puntaje;
    }

    public abstract void aplicarModificador();
}
