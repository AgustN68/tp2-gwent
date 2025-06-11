package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Modificador.Modificador;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.exceptions.UnidadNoSePuedeUbicarEnEstaSeccionException;

import java.util.List;
import java.util.ArrayList;

public class Unidad implements Carta {

    private Puntaje puntaje;
    private Modificador modificador;
    private List<Seccion> secciones;

    public Unidad(Seccion seccion, int puntosIniciales, Modificador modificador) {
        this.secciones = new ArrayList<>();
        secciones.add(seccion);

        puntaje = new Puntaje(puntosIniciales);
        this.modificador = modificador;
    }

    @Override
    public void usar(){
        this.ubicar(this.secciones.get(0));
        this.modificador.aplicarModificador();
    }

    public void usar(Seccion seccion) {
        ubicar(seccion);
    }


    public void ubicar(Seccion seccionAUbicar) {
        if (seccionPuedeUbicar(seccionAUbicar)){
            seccionAUbicar.ubicar(this);
        } else {
            throw new UnidadNoSePuedeUbicarEnEstaSeccionException("No se puede ubicar en esta seccion");
        }

    }

    private Boolean seccionPuedeUbicar(Seccion seccion) {
        for (Seccion seccionPermitida : secciones) {
            if (seccion.equals(seccionPermitida)) {
                return true;
            }
        }
        return false;
    }

    public void actualizarSecciones(List<Seccion> nuevasSeccionesPermitidas) {
        this.secciones.clear();
        this.secciones = nuevasSeccionesPermitidas;
    }


    public Puntaje calcularPuntaje(){
        return modificador.aplicarModificador(puntaje);
    }

    public void actualizarPuntaje(int valor){
        puntaje.modificarValor(valor);
    }

    public void multiplicarPuntaje(int multiplicador){
        puntaje.multiplicarValor(multiplicador);
    }

    public void restaurarPuntaje() {
        puntaje.reiniciarValor();
    }


    public boolean esMasFuerte(Unidad unidad) {
        return puntaje.esMayor(unidad.calcularPuntaje());
    }
}
