package edu.fiuba.algo3.modelo;

public class Tablero {
    private final Seccion seccionCuerpoACuerpo;
    private final Seccion secconRango;
    private final Seccion seccionAsedio;

    public Tablero(Seccion seccionCuerpoACuerpo, Seccion secconRango, Seccion seccionAsedio) {
        this.seccionCuerpoACuerpo = seccionCuerpoACuerpo;
        this.secconRango = secconRango;
        this.seccionAsedio = seccionAsedio;
    }

    public void ubicar(Unidad unidad) {
    }
}
