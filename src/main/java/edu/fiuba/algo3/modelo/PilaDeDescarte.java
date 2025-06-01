package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.Carta;



import java.util.ArrayList;
import java.util.List;

public class PilaDeDescarte {
    private List<Carta> descartes;
    public PilaDeDescarte(){
        this.descartes = new ArrayList<>();
    }

     public void descartarUna(Carta carta){
        this.descartes.add(carta);
    }

    public void descartarCartas(List<Carta> descartes){
        this.descartes.addAll(descartes);
    }

    public void mostrarDescartes(){
       // this.descartes.mostrar(); No esta implementado en cartas.
    }
}
