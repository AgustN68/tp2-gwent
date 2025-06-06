package edu.fiuba.algo3.modelo.Carta.Especial.Clima;

import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Seccion.Seccion;

public class Clima extends Especial {

    protected static final int VALOR = 1;

    protected Seccion seccionAfectada1;
    protected Seccion seccionAfectada2;

    public Clima(Seccion seccion1, Seccion seccion2) {
        seccionAfectada1 = seccion1;
        seccionAfectada2 = seccion2;
    }

    public void usar() {
        seccionAfectada1.reducirPuntaje(VALOR);
        seccionAfectada2.reducirPuntaje(VALOR);
    }
}
