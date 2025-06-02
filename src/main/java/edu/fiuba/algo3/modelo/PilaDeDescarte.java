package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.Carta.Carta;

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

    public void mostrarDescartes(){
       // this.cartas.mostrar(); No esta implementado en cartas.
    }

    public Carta usarCartaMedico(int posicion){
        return this.cartas.remove(posicion);
    }
}
