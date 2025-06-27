package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima.Clima;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima.ClimaPosible;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima.Despejado;
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

    public void limpiarClima(Despejado climaDespejado) {
        for (Seccion seccion : secciones) {
            seccion.establecerClima(climaDespejado);
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



