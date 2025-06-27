package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Modificador.Modificador;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.exceptions.UnidadNoSePuedeUbicarEnEstaSeccionException;

import java.util.List;
import java.util.ArrayList;

public class Unidad extends Carta {

    private Puntaje puntaje;
    private final Modificador modificador;
    private List<Seccion> secciones;

    private static final int POS_SECCION_PARTICULAR = 0;

    public Unidad(String nombre, Seccion seccion, int puntosIniciales, Modificador modificador) {
        this.setNombre(nombre);
        this.secciones = new ArrayList<>();
        secciones.add(seccion);
        puntaje = new Puntaje(puntosIniciales);
        this.modificador = modificador;
    }

    @Override
    public void usar() {

    }

    @Override
    public void usar(Seccion seccion) {
        this.ubicar(seccion);
        this.modificador.aplicarModificador();
    }

    public void ubicar(Seccion seccionAUbicar) {
        if (!secciones.contains(seccionAUbicar)) {
            throw new UnidadNoSePuedeUbicarEnEstaSeccionException("No se puede ubicar en esta seccion");
        }
        seccionAUbicar.ubicar(this);
    }

    public void actualizarSecciones(List<Seccion> nuevasSeccionesPermitidas) {
        this.secciones.clear();
        this.secciones = nuevasSeccionesPermitidas;
    }

    public Boolean esIgual(Unidad unidad) {
        return this.getNombre().equals(unidad.getNombre());
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

    public Puntaje getPuntaje() {
        return puntaje;
    }

    public List<Seccion> obtenerSecciones() {
        return secciones;
    }

    public Modificador obtenerModificador() {
        return modificador;
    }
}

