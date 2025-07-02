package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Seccion.Seccion;

import java.util.List;

public abstract class Carta {

    private String nombre;

    private String descripcion;

    public abstract void usar();

    public abstract void usar(Seccion seccion);

    public abstract void usar(List<Seccion> secciones);

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public abstract String obtenerDescripcion();
}
