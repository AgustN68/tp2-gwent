package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasEspecialesSuficientesException;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasUnidadSuficientesException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {

    private ArrayList<Carta> cartas;
    private static final int CANT_MIN_UNIDADES = 15;
    private static final int CANT_MIN_ESPECIALES = 6;

    public Mazo(List<Unidad> unidades, List<Especial> especiales) {
        validarUnidades(unidades, CANT_MIN_UNIDADES);
        validarEspeciales(especiales, CANT_MIN_ESPECIALES);
        this.cartas = mezclarCartas(unidades, especiales);
    }

    private void validarUnidades(List<Unidad> unidades, int cant){
        if (unidades.size() < cant) {
            throw new NoHayCartasUnidadSuficientesException("El mazo no tiene unidades suficientes");
        }
    }

    private void validarEspeciales(List<Especial> especiales, int cant){
        if (especiales.size() < cant) {
            throw new NoHayCartasEspecialesSuficientesException("El mazo no tiene cartas especiales suficientes");
        }
    }

    private ArrayList<Carta> mezclarCartas(List<Unidad> unidades ,List<Especial> especiales){
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.addAll(unidades);
        cartas.addAll(especiales);
        Collections.shuffle(cartas);
        return cartas;
    }

    // TODO: obtener carta aleatoria y quitarla
    public Carta obtenerCarta() {
        return cartas.remove((cartas.size() - 1));
    }

    public int obtenerCantidadDeCartas() {
        return cartas.size();
    }
}
