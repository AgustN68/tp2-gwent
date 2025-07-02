package edu.fiuba.algo3.modelo.Carta.Especial.Clima;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import java.util.List;

public class SinClima implements ClimaPosible {

    private String nombre;

    public SinClima() {
        this.nombre = "Tiempo despejado";
    }
    @Override
    public void modificarPuntaje(List<Unidad> unidades) {
        // No modifica ningún puntaje
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}
