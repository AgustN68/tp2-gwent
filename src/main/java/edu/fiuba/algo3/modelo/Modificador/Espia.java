package edu.fiuba.algo3.modelo.Modificador;

import edu.fiuba.algo3.modelo.Jugador;

public class Espia extends Modificador {
    private final Jugador jugador;
    private String descripcion = "Espia: Se puede colocar en el campo de batalla del oponente (y cuentan para el total de cartas) y permite robar 2 cartas adicionales de tu mazo.";

    public Espia(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public void aplicarModificador() {
        jugador.tomarCartasEspia();
    }
    @Override
    public String obtenerDescripcion() {
        return this.descripcion;
    }
}
