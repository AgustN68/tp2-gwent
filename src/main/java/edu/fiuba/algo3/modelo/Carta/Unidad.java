package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Modificador.Modificador;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.exceptions.UnidadNoSePuedeUbicarEnEstaSeccionException;

import java.util.List;
import java.util.ArrayList;

public class Unidad implements Carta {

    private final String nombre;
    private Puntaje puntaje;
    private final Modificador modificador;
    private List<Seccion> secciones;

    private static final int POS_SECCION_PARTICULAR = 0;

    public Unidad(String nombre, Seccion seccion, int puntosIniciales, Modificador modificador) {
        this.nombre = nombre;
        this.secciones = new ArrayList<>();
        secciones.add(seccion);
        puntaje = new Puntaje(puntosIniciales);
        this.modificador = modificador;
    }

    @Override
    public void usar() {
        this.ubicar(this.secciones.get(POS_SECCION_PARTICULAR));
        this.modificador.aplicarModificador();
    }

    public void usar(Seccion seccion) {
        ubicar(seccion);
    }


    public void ubicar(Seccion seccionAUbicar) {
        if (seccionPuedeUbicar(seccionAUbicar)) {
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

    public String obtenerNombre() {
        return this.nombre;
    }

    public Boolean esIgual(Unidad unidad) {
        return this.nombre.equals(unidad.obtenerNombre());
    }


    public Puntaje calcularPuntaje() {
        return modificador.aplicarModificador(puntaje);
    }

    public void actualizarPuntaje(int valor) {
        modificador.aplicarEfectoClima(puntaje, valor);
    }

    public void multiplicarPuntaje(int multiplicador) {
        modificador.aplicarMultiplicacion(puntaje, multiplicador);
    }

    public void sumaPuntaje(int suma) {
        secciones.get(POS_SECCION_PARTICULAR).sumarPuntaje(suma);
    }

    public void sumarPuntaje(int suma) {
        modificador.aplicarSuma(puntaje, suma);
    }
    public void restaurarPuntaje() {
        puntaje.reiniciarValor();
    }

    public boolean esVulnerableAEspeciales() {
        return modificador.esVulnerableAEspeciales();
    }

    public boolean esMasFuerte(Unidad unidad) {
        return puntaje.esMayor(unidad.calcularPuntaje());
    }

    public boolean tienePuntaje(Puntaje puntaje) {
        return this.puntaje.equalsPuntaje(puntaje);
    }

    public Puntaje actualizarPuntajeSegunCantMismoTipoEnSeccion(Puntaje puntaje) {
        return secciones.get(POS_SECCION_PARTICULAR).actualizarPuntajeSegunCantidadEnSeccion(this, puntaje);
    }

    public String getNombre() {
        return nombre;
    }

    public Puntaje getPuntaje() {
        return puntaje;
    }
}

