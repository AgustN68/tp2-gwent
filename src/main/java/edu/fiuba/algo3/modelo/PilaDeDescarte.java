package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.Carta.Carta;

import java.util.ArrayList;
import java.util.List;

public class PilaDeDescarte implements  Mostrable {
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

    public List <Carta> mostrar(){
        return this.cartas;         //ver que vamos a mostrar, carta deberia poder mostrar algo al jugador
    }
    public int cantidadCartas(){
        return this.cartas.size();
    }
    public Carta agarrarCarta(){
        return this.cartas.remove(0);
    }
}
