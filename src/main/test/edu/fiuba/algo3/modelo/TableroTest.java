package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.colocadores.ColocadorCuerpoACuerpo;
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
        new Unidad(new ColocadorCuerpoACuerpo(), 4, new Modificador()).usar(tablero);
        new Unidad(new ColocadorCuerpoACuerpo(), 2, new Modificador()).usar(tablero);

        //"Assert"
        assertEquals(6, tablero.calcularPuntaje().obtenerValor());
    }
}
