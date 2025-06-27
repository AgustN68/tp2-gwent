package edu.fiuba.algo3.modelo.Carta.Especial;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Tablero;

import java.util.List;

public class TierraArrasada extends Especial {


    public void usar(List<Seccion> secciones) {
        System.out.println("buscando carta con mayor puntaje");
        Puntaje puntajeMasFuerte = new Puntaje(0);
        for (Seccion seccion : secciones) {
            Puntaje puntajeMasFuerteSeccionActual = seccion.obtenerPuntajeMasFuerte();
            System.out.println("puntaje maximo de " + seccion.getClass().getSimpleName() + ": " + puntajeMasFuerteSeccionActual);
            if (!puntajeMasFuerte.esMayor(puntajeMasFuerteSeccionActual)) {
                puntajeMasFuerte = puntajeMasFuerteSeccionActual;
            }
        }
        System.out.println("puntaje mas fuerte: " + puntajeMasFuerte);
        System.out.println("removiendo cartas de puntaje " + puntajeMasFuerte + " de todas las secciones");
        for (Seccion seccion : secciones) {
            seccion.removerCartasDePuntaje(puntajeMasFuerte);
        }
    }

    @Override
    public void usar() {

    }

    @Override
    public void usar(Seccion seccion) {

    }

}
