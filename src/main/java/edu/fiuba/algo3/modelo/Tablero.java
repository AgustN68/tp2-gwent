package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Seccion.Seccion;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private final List<Seccion> secciones;

    public Tablero(List<Seccion> secciones) {
        this.secciones = secciones;
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

    public List<Seccion> getSecciones() {
        return secciones;
    }

    public List<Carta> limpiarTablero() {
        List<Carta> cartas = new ArrayList<>();
        for (Seccion seccion : secciones) {
            cartas.addAll(seccion.limpiarSeccion());
        }
        return cartas;
    }
}
