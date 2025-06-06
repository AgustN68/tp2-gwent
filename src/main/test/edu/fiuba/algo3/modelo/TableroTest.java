package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.Modificador;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableroTest {
    @Test
    public void test01UnTableroPuedeCalcularSuPuntajeTotal(){
        //"Arrange"
        Seccion seccionCuerpoACuerpo = new CuerpoACuerpo();
        Seccion secconRango = new Rango();
        Seccion seccionAsedio = new Asedio();
        Tablero tablero = new Tablero(seccionCuerpoACuerpo, secconRango, seccionAsedio);

        //"Act"
        new Unidad(seccionCuerpoACuerpo, 4, new Modificador()).ubicar(seccionCuerpoACuerpo);
        new Unidad(seccionCuerpoACuerpo, 2, new Modificador()).ubicar(seccionCuerpoACuerpo);

        //"Assert"
        assertEquals(6, tablero.calcularPuntaje().obtenerValor());
    }
}
