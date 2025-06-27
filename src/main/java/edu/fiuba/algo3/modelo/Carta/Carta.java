package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Seccion.Seccion;

public abstract class Carta {

    private String nombre;

    public abstract void usar();

    public abstract void usar(Seccion seccion);

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return this.nombre;
    }
}
