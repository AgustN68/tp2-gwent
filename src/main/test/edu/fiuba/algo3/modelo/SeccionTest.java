package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.Modificador;
import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SeccionTest {

    @Test
    public void test01UnaSeccionSinUnidadesTienePuntajeCero() {
        Seccion seccion = new CuerpoACuerpo();
        assertEquals(0, seccion.puntajeTotal().obtenerValor());
    }

    @Test
    public void test02UnaSeccionConUnaUnidadDevuelveSuPuntaje() {
        Seccion seccion = new CuerpoACuerpo();
        Modificador modificador = new SinModificador();
        Asedio seccionAsedio = new Asedio();
        Unidad unidad = new Unidad(seccionAsedio, 7, modificador);

        seccion.ubicar(unidad);

        assertEquals(7, seccion.puntajeTotal().obtenerValor());
    }

    @Test
    public void test03UnaSeccionConMultiplesUnidadesSumaLosPuntajes() {
        Seccion seccion = new Asedio();
        Modificador modificador = new SinModificador();
        Asedio seccionAsedio = new Asedio();

        seccion.ubicar(new Unidad(seccionAsedio, 3, modificador));
        seccion.ubicar(new Unidad(seccionAsedio, 5, modificador));
        seccion.ubicar(new Unidad(seccionAsedio, 2, modificador));

        assertEquals(10, seccion.puntajeTotal().obtenerValor());
    }
}
