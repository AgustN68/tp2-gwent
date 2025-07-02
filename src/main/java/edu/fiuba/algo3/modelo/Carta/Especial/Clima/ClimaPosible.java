package edu.fiuba.algo3.modelo.Carta.Especial.Clima;

import edu.fiuba.algo3.modelo.Carta.Unidad;

import java.util.List;

public interface ClimaPosible {

    void modificarPuntaje(List<Unidad> unidades);

    String getNombre();
}
