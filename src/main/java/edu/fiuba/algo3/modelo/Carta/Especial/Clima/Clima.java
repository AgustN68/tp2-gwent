package edu.fiuba.algo3.modelo.Carta.Especial.Clima;

import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Seccion.Seccion;

import java.util.List;

public class Clima extends Especial implements ClimaPosible {

    private String nombre;
    protected static final int VALOR = 1;

    protected List<Seccion> seccionesAfectadas;

    public Clima(List<Seccion> secciones, String nombre) {
        seccionesAfectadas = secciones;
        this.nombre = nombre;
    }

    @Override
    public void usar() {
        for (Seccion seccion : seccionesAfectadas) {
            seccion.establecerClima(this);
        }
    }

    @Override
    public void usar(Seccion seccion) {

    }

    @Override
    public void usar(List<Seccion> secciones) {
    }

    @Override
    public void modificarPuntaje(List<Unidad> unidades){
        for (Unidad unidad : unidades){
            unidad.actualizarPuntaje(VALOR);
        }
    }

    public String getNombre() {
        return nombre;
    }
}
