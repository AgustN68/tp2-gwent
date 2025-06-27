package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasSuficientesException;

import java.util.List;

public class Mano {

    private List<Carta> cartas;

    public Mano(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public void tieneCartasSuficientes(int cantidadCartas) {
        if (this.cartas.size() < cantidadCartas) {
            throw new NoHayCartasSuficientesException("La mano no tiene cartas suficientes");
        }
    }

    public void tomarCarta(Mazo mazo) {
        cartas.add(mazo.obtenerCarta());
    }

    public Carta agarrarCarta(int unaPosicion){
        if (unaPosicion < 0 || unaPosicion >= cartas.size()) {
            throw new IndexOutOfBoundsException("Posición inválida");
        }
        return cartas.remove(unaPosicion);
    }

    public List<Carta> obtenerCartas() {
        return cartas;
    }
}
