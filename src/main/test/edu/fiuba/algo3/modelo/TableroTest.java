package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableroTest {
    @Test
    public void test01UnTableroPuedeCalcularSuPuntajeTotal(){
        //"Arrange"
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);

        Tablero tablero = new Tablero(secciones);

        //"Act"
        new Unidad(cuerpoACuerpo, 4, new SinModificador()).ubicar(cuerpoACuerpo);
        new Unidad(cuerpoACuerpo, 2, new SinModificador()).ubicar(cuerpoACuerpo);

        //"Assert"
        assertEquals(6, tablero.calcularPuntaje().obtenerValor());
    }
}
