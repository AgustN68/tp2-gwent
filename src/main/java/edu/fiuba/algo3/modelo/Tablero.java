package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Unidad;

public class Tablero {
    private final Seccion seccionCuerpoACuerpo;
    private final Seccion seccionRango;
    private final Seccion seccionAsedio;

    public Tablero(Seccion seccionCuerpoACuerpo, Seccion seccionRango, Seccion seccionAsedio) {
        this.seccionCuerpoACuerpo = seccionCuerpoACuerpo;
        this.seccionRango = seccionRango;
        this.seccionAsedio = seccionAsedio;
    }

    public void ubicar(Unidad unidad, Seccion seccion) {

    }
}
