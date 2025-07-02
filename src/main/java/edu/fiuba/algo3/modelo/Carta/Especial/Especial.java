package edu.fiuba.algo3.modelo.Carta.Especial;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Tablero;

public abstract class Especial extends Carta {

    private String nombre;

    private String descripcion;

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String obtenerDescripcion() {
        return this.descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}


