package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SeccionTest {

    @Test
    public void test01UnaSeccionSinUnidadesTienePuntajeCero() {
        Seccion seccion = new Seccion();
        assertEquals(0, seccion.puntajeTotal().obtenerValor());
    }

    @Test
    public void test02UnaSeccionConUnaUnidadDevuelveSuPuntaje() {
        Seccion seccion = new Seccion();
        Modificador modificador = new Modificador();
        Unidad unidad = new Unidad(seccion, 7, modificador);

        seccion.ubicar(unidad);

        assertEquals(7, seccion.puntajeTotal().obtenerValor());
    }

    @Test
    public void test03UnaSeccionConMultiplesUnidadesSumaLosPuntajes() {
        Seccion seccion = new Seccion();
        Modificador modificador = new Modificador();

        seccion.ubicar(new Unidad(seccion, 3, modificador));
        seccion.ubicar(new Unidad(seccion, 5, modificador));
        seccion.ubicar(new Unidad(seccion, 2, modificador));

        assertEquals(10, seccion.puntajeTotal().obtenerValor());
    }
}
