package edu.fiuba.algo3.modelo.Seccion;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Puntaje;

import java.util.ArrayList;
import java.util.List;

public abstract class Seccion {

    private List<Unidad> cartasUnidades;
    private Puntaje puntaje;

    public Seccion() {
        cartasUnidades = new ArrayList<>();
        puntaje = new Puntaje(0);
    }
    
    public void reducirPuntaje(int valor){
        for (Unidad unidad : cartasUnidades){
            unidad.actualizarPuntaje(valor);
        }
    }
    
    public void ubicar(Unidad unidad){
        cartasUnidades.add(unidad);
    }

    public Puntaje puntajeTotal() {
        for (Unidad unidad : cartasUnidades) {
            puntaje = puntaje.sumarPuntaje(unidad.calcularPuntaje());
        }
        return puntaje;
    }


    public void multiplicarPuntaje(int producto) {
        for (Unidad unidad : cartasUnidades) {
            unidad.multiplicarPuntaje(producto);
        }
    }

    public void restaurarPuntaje() {
        for (Unidad unidad : cartasUnidades) {
            unidad.restaurarPuntaje();
        }
    }
}
