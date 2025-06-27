package edu.fiuba.algo3.modelo.Carta.Especial;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Tablero;

import java.util.List;

public class TierraArrasada extends Especial {


    public void usar(List<Seccion> secciones) {
        Puntaje puntajeMasFuerte = new Puntaje(0);
        for (Seccion seccion : secciones) {
            Puntaje puntajeMasFuerteSeccionActual = seccion.obtenerPuntajeMasFuerte();
            if (!puntajeMasFuerte.esMayor(puntajeMasFuerteSeccionActual)) {
                puntajeMasFuerte = puntajeMasFuerteSeccionActual;
            }
        }
    }

    @Override
    public void usar() {

    }

    @Override
    public void usar(Seccion seccion) {

    }

}
