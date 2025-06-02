package edu.fiuba.algo3.modelo.CartaTest;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableroTest {
    @Test
    public void test01UnTableroPuedeCalcularSuPuntajeTotal(){
        //"Arrange"
        Seccion seccionCuerpoACuerpo = new Seccion();
        Seccion secconRango = new Seccion();
        Seccion seccionAsedio = new Seccion();
        Tablero tablero = new Tablero(seccionCuerpoACuerpo, secconRango, seccionAsedio);

        //"Act"
        new Unidad(seccionCuerpoACuerpo, 4, new Modificador()).usar(tablero);
        new Unidad(seccionCuerpoACuerpo, 2, new Modificador()).usar(tablero);

        //"Assert"
        assertEquals(6, tablero.calcularPuntaje().obtenerValor());
    }
}
