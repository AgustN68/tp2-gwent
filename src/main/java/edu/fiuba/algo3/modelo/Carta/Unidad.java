package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Modificador.Modificador;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.exceptions.UnidadNoSePuedeUbicarEnEstaSeccionException;

public class Unidad implements Carta {

    private Puntaje puntaje;
    private Modificador modificador;
    private Seccion seccion;

    public Unidad(Seccion seccion, int puntosIniciales, Modificador modificador) {
        this.seccion = seccion;
        puntaje = new Puntaje(puntosIniciales);
        this.modificador = modificador;
    }

    @Override
    public void usar(){
        this.ubicar(this.seccion);
        this.modificador.aplicarModificador();
    }

    public void ubicar(Seccion seccionAUbicar) {
        if (seccionPuedeUbicar(seccionAUbicar)){
            seccionAUbicar.ubicar(this);
        } else {
            throw new UnidadNoSePuedeUbicarEnEstaSeccionException("No se puede ubicar en esta seccion");
        }

    }

    public Boolean seccionPuedeUbicar(Seccion seccion) {
        return seccion.equals(this.seccion);
    }

    public Puntaje calcularPuntaje(){
        return this.modificador.aplicarModificador(puntaje);
    }

    public void reducirPuntaje(int valor){
        this.puntaje.modificarValor(valor);
    }
}
