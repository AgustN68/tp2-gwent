package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.CartaNula;

import java.util.ArrayList;
import java.util.List;

public class PilaDeDescarte {
    private List<Carta> cartas;
    public PilaDeDescarte(){
        this.cartas = new ArrayList<>();
    }

     public void descartarUna(Carta carta){
        this.cartas.add(carta);
    }

    public void descartarCartas(List<Carta> descartes){
        this.cartas.addAll(descartes);
    }

    public int cantidadCartas(){
        return this.cartas.size();
    }
    public Carta agarrarCarta() {
        return cantidadCartas() != 0 ? this.cartas.remove(0) : new CartaNula();
    }
}
