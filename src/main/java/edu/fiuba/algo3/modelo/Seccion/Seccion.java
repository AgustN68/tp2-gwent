package edu.fiuba.algo3.modelo.Seccion;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima.Clima;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima.ClimaPosible;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima.Despejado;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Puntaje;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Seccion {

    private List<Unidad> cartasUnidades;

    private ClimaPosible climaActual;

    public Seccion() {
        cartasUnidades = new ArrayList<>();
    }
    
    public void reducirPuntaje(int valor){
        if (valor < 0) {
            throw new IllegalArgumentException("El valor no puede ser negativo");
        }
        for (Unidad unidad : cartasUnidades){
            unidad.actualizarPuntaje(valor);
        }
    }
    
    public void ubicar(Unidad unidad){
        cartasUnidades.add(unidad);
        climaActual.modificarPuntaje(cartasUnidades);
    }

    public Puntaje puntajeTotal() {
        Puntaje puntajeTotal = new Puntaje(0);
        for (Unidad unidad : cartasUnidades) {
            puntajeTotal = puntajeTotal.sumarPuntaje(unidad.calcularPuntaje());
        }
        return puntajeTotal;
    }


    public void multiplicarPuntaje(int producto) {
        if (producto <= 0) {
            throw new IllegalArgumentException("El producto debe ser mayor que cero");
        }
        for (Unidad unidad : cartasUnidades) {
            unidad.multiplicarPuntaje(producto);
        }
    }

    public void sumarPuntaje(int suma) {
        if (suma < 0) {
            throw new IllegalArgumentException("La suma no puede ser negativa");
        }
        for (Unidad unidad : cartasUnidades) {
            unidad.sumarPuntaje(suma);
        }
    }

    public void restaurarPuntaje() {
        for (Unidad unidad : cartasUnidades) {
            unidad.restaurarPuntaje();
        }
    }

    public Puntaje obtenerPuntajeMasFuerte() {
        if (cartasUnidades.isEmpty()) return new Puntaje(0);
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

    public List<Unidad> getCartasUnidades() {
        return cartasUnidades;
    }

    public List<Carta> limpiarSeccion() {
        List<Carta> cartas = new ArrayList<>(cartasUnidades);
        cartasUnidades.clear();
        return cartas;
    }

    public void establecerClima(ClimaPosible clima){
        // Restaurar puntaje antes de aplicar nuevo clima
        restaurarPuntaje();
        climaActual = clima;
        climaActual.modificarPuntaje(cartasUnidades);
    }
/*
    public void limpiarClima(){
        restaurarPuntaje();
        climaActual = null;
    }*/
}

