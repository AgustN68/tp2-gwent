package edu.fiuba.algo3.modelo.Seccion;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Puntaje;

import java.util.ArrayList;
import java.util.List;

public abstract class Seccion {

    private List<Unidad> cartasUnidades;

    public Seccion() {
        cartasUnidades = new ArrayList<>();
    }
    
    public void reducirPuntaje(int valor){
        for (Unidad unidad : cartasUnidades){
            unidad.actualizarPuntaje(valor);
        }
    }
    
    public void ubicar(Unidad unidad){
        cartasUnidades.add(unidad);
    }

    public Puntaje puntajeTotal() {
        Puntaje puntajeTotal = new Puntaje(0);
        for (Unidad unidad : cartasUnidades) {
            puntajeTotal = puntajeTotal.sumarPuntaje(unidad.calcularPuntaje());
        }
        return puntajeTotal;
    }


    public void multiplicarPuntaje(int producto) {
        for (Unidad unidad : cartasUnidades) {
            unidad.multiplicarPuntaje(producto);
        }
    }

    public void restaurarPuntaje() {
        for (Unidad unidad : cartasUnidades) {
            unidad.restaurarPuntaje();
        }
    }

    public Puntaje obtenerPuntajeMasFuerte() {
        if (cartasUnidades.size() == 0) return new Puntaje(0);
        Unidad unidadMasFuerte = cartasUnidades.get(0);
        for (Unidad unidad : cartasUnidades) {
            unidadMasFuerte = unidad.esMasFuerte(unidadMasFuerte) ? unidad : unidadMasFuerte;
        }
        return unidadMasFuerte.calcularPuntaje();
    }

    public void removerCartasDePuntaje(Puntaje puntaje) {
        cartasUnidades.removeIf(unidad -> unidad.tienePuntaje(puntaje) && unidad.esVulnerableAEspeciales());
    }

    public Puntaje actualizarPuntajeSegunCantidadEnSeccion(Unidad unidad, Puntaje puntaje) {
        int cantidad = 0;
        for (Unidad carta : cartasUnidades) {
            if (carta.esIgual(unidad)) {
                cantidad++;
            }
        }
        return puntaje.multiplicarPuntaje(new Puntaje(cantidad));
    }
}

