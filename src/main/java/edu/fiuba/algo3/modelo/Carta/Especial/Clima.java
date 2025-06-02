package edu.fiuba.algo3.modelo.Carta.Especial;

import edu.fiuba.algo3.modelo.Seccion;

public abstract class Clima {

    protected static final int VALOR = 1;

    protected Seccion seccionAfectada1;
    protected Seccion seccionAfectada2;

    public Clima(Seccion seccion1, Seccion seccion2) {
        seccionAfectada1 = seccion1;
        seccionAfectada2 = seccion2;
    }

    protected void usar(){
        seccionAfectada1.reducirPuntaje(VALOR);
        seccionAfectada2.reducirPuntaje(VALOR);
    }
}
