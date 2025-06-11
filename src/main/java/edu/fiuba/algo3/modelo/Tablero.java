package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.Unida;
import edu.fiuba.algo3.modelo.Seccion.Seccion;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private final List<Seccion> secciones;

    /*
    public Tablero(Seccion seccionCuerpoACuerpo, Seccion seccionRango, Seccion seccionAsedio) {
        this.seccionCuerpoACuerpo = seccionCuerpoACuerpo;
        this.seccionRango = seccionRango;
        this.seccionAsedio = seccionAsedio;
    }
    */

    public Tablero(List<Seccion> secciones) {
        this.secciones = secciones;
    }

    public Tablero(Seccion seccionCuerpoACuerpo, Seccion seccionRango, Seccion seccionAsedio) {
        this.secciones = new ArrayList<>();
        this.secciones.add(seccionCuerpoACuerpo);
        this.secciones.add(seccionRango);
        this.secciones.add(seccionAsedio);
    }

    public Puntaje calcularPuntaje() {
        Puntaje puntajeTotal = new Puntaje(0);
        for (Seccion seccion : secciones) {
            puntajeTotal = puntajeTotal.sumarPuntaje(seccion.puntajeTotal());
        }
        return puntajeTotal;
    }

    public void limpiarClima() {
        for (Seccion seccion : secciones) {
            seccion.restaurarPuntaje();
        }
    }

    public void removerUnidadesMasFuertes() {
        Puntaje puntajeMasFuerte = new Puntaje(0);

        for (Seccion seccion : secciones) {
            Puntaje puntajeMasFuerteSeccionActual = seccion.obtenerPuntajeMasFuerte();
            if (!puntajeMasFuerte.esMayor(puntajeMasFuerteSeccionActual)) {
                puntajeMasFuerte = puntajeMasFuerteSeccionActual;
            }
        }

        for (Seccion seccion : secciones) {
            seccion.removerCartasDePuntaje(puntajeMasFuerte);
        }
    }
}
