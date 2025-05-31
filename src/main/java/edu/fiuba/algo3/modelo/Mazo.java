package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.exceptions.NoHayCartasEspecialesSuficientesException;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasUnidadSuficientesException;

import java.util.List;

public class Mazo {

    private List<Unidad> cartasUnidades;
    private List<Especial> cartasEspeciales;

    public Mazo(List<Unidad> cartasUnidad, List<Especial> cartasEspeciales) {
        this.cartasUnidades = cartasUnidad;
        this.cartasEspeciales = cartasEspeciales;
    }

    public void validarCartasSuficientes(int cantUnidadMin, int cantEspecialMin){
        validarCartasUnidadSuficientes(cantUnidadMin);
        validarCartasEspecialSuficientes(cantEspecialMin);
    }

    private void validarCartasUnidadSuficientes(int cant){
        if (cartasUnidades.size() < cant) {
            throw new NoHayCartasUnidadSuficientesException("El mazo no tiene unidades suficientes");
        }
    }

    private void validarCartasEspecialSuficientes(int cant){
        if (cartasEspeciales.size() < cant) {
            throw new NoHayCartasEspecialesSuficientesException("El mazo no tiene cartas especiales suficientes");
        }
    }

    // TODO: obtener carta aleatoria y quitarla
    public Carta obtenerCarta() {
        return cartasUnidades.get(0);
    }
}
