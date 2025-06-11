package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Especial.Clima;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Seccion.Seccion;

public class Tablero {
    private final Seccion seccionCuerpoACuerpo;
    private final Seccion seccionRango;
    private final Seccion seccionAsedio;

    public Tablero(Seccion seccionCuerpoACuerpo, Seccion seccionRango, Seccion seccionAsedio) {
        this.seccionCuerpoACuerpo = seccionCuerpoACuerpo;
        this.seccionRango = seccionRango;
        this.seccionAsedio = seccionAsedio;
    }

    public void ubicarCuerpoACuerpo(Unidad unidad) {
        seccionCuerpoACuerpo.ubicar(unidad);
    }

    public void ubicarRango(Unidad unidad) {
        seccionRango.ubicar(unidad);
    }

    public void ubicarAsedio(Unidad unidad) {
        seccionAsedio.ubicar(unidad);
    }

    public Puntaje calcularPuntaje() {
        Puntaje puntajeTotal = new Puntaje(0);
        puntajeTotal = puntajeTotal.sumarPuntaje(seccionCuerpoACuerpo.puntajeTotal());
        puntajeTotal = puntajeTotal.sumarPuntaje(seccionRango.puntajeTotal());
        puntajeTotal = puntajeTotal.sumarPuntaje(seccionAsedio.puntajeTotal());
        return puntajeTotal;
    }

    public void limpiarClima() {
        seccionCuerpoACuerpo.restaurarPuntaje();
        seccionRango.restaurarPuntaje();
        seccionAsedio.restaurarPuntaje();
    }

    public void removerUnidadMasFuerte() {

        Seccion seccionUnidadMasFuerte = seccionCuerpoACuerpo;
        Unidad unidadMasFuerte = seccionCuerpoACuerpo.obtenerUnidadMasFuerte();

        Unidad unidadMasFuerteRango = seccionRango.obtenerUnidadMasFuerte();
        if (unidadMasFuerteRango.esMasFuerte(unidadMasFuerte)) {
            seccionUnidadMasFuerte = seccionRango;
            unidadMasFuerte = unidadMasFuerteRango;
        }

        Unidad unidadMasFuerteAsedio = seccionAsedio.obtenerUnidadMasFuerte();
        if (unidadMasFuerteAsedio.esMasFuerte(unidadMasFuerte)) {
            seccionUnidadMasFuerte = seccionAsedio;
            unidadMasFuerte = unidadMasFuerteAsedio;
        }

        seccionUnidadMasFuerte.remover(unidadMasFuerte);
    }
}
