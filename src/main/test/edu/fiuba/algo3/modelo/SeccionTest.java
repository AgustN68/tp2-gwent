package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.colocadores.Colocador;
import edu.fiuba.algo3.modelo.colocadores.ColocadorAsedio;
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
        ColocadorAsedio colocadorAsedio = new ColocadorAsedio();
        Unidad unidad = new Unidad(colocadorAsedio, 7, modificador);

        seccion.ubicar(unidad);

        assertEquals(7, seccion.puntajeTotal().obtenerValor());
    }

    @Test
    public void test03UnaSeccionConMultiplesUnidadesSumaLosPuntajes() {
        Seccion seccion = new Seccion();
        Modificador modificador = new Modificador();
        ColocadorAsedio colocadorAsedio = new ColocadorAsedio();

        seccion.ubicar(new Unidad(colocadorAsedio, 3, modificador));
        seccion.ubicar(new Unidad(colocadorAsedio, 5, modificador));
        seccion.ubicar(new Unidad(colocadorAsedio, 2, modificador));

        assertEquals(10, seccion.puntajeTotal().obtenerValor());
    }
}
