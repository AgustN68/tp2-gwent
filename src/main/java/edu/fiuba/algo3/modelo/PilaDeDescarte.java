package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.CartaNula;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PilaDeDescarte {
    private List<Carta> cartas;
    private static final Random random = new Random();

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
        if (cantidadCartas() != 0) {
            int idx = random.nextInt(cartas.size());
            return this.cartas.remove(idx);
        } else {
            return new CartaNula();
        }
    }
}
