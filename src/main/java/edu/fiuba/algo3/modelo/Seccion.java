package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Unidad;

import java.util.ArrayList;
import java.util.List;

public class Seccion {

    private List<Unidad> cartasUnidades;

    public Seccion() {
        cartasUnidades = new ArrayList<>();
    }
    
    public void reducirPuntaje(int valor){
        for (Unidad unidad : cartasUnidades){
            unidad.reducirPuntaje(valor);
        }
    }
    
    public void ubicar(Unidad unidad){
        cartasUnidades.add(unidad);
    }

    public Puntaje puntajeTotal() {
        Puntaje total = new Puntaje(0);
        for (Unidad unidad : cartasUnidades) {
            total = total.sumarPuntaje(unidad.calcularPuntaje());
        }
        return total;
    }

}
