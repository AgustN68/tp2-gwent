package edu.fiuba.algo3.modelo.CartaTest.EspecialTest;

import edu.fiuba.algo3.modelo.Carta.Especial.TierraArrasada;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Tablero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TierraArrasadaTest {
    @Test
    public void test01SeUsaTierraArrasadaYSeEliminanLasCartasCorrectamente() {
        // Arrange
        CuerpoACuerpo cuerpoACuerpo = new CuerpoACuerpo();
        Rango rango = new Rango();
        Asedio asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);
        Tablero tablero = new Tablero(secciones);

        Unidad unidad2 = new Unidad("Nombre", cuerpoACuerpo, 9, new SinModificador());
        Unidad unidad3 = new Unidad("Nombre", rango, 10, new SinModificador());

        unidad2.ubicar(cuerpoACuerpo);
        unidad3.ubicar(rango);

        // Act
        TierraArrasada tierraArrasada = new TierraArrasada();

        tierraArrasada.usar(tablero);

        Puntaje puntajeObtenido = tablero.calcularPuntaje();

        // Assert
        assertEquals(9, puntajeObtenido.obtenerValor());
    }

    @Test
    public void test02SeUsaTierraArrasadaYSeEliminanLasCartasCorrectamente() {
        // Arrange
        CuerpoACuerpo cuerpoACuerpo = new CuerpoACuerpo();
        Rango rango = new Rango();
        Asedio asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);
        Tablero tablero = new Tablero(secciones);

        Unidad unidad1 = new Unidad("Nombre", cuerpoACuerpo, 8, new SinModificador());
        Unidad unidad2 = new Unidad("Nombre", cuerpoACuerpo, 9, new SinModificador());
        Unidad unidad3 = new Unidad("Nombre", rango, 10, new SinModificador());
        Unidad unidad4 = new Unidad("Nombre", asedio, 14, new SinModificador());

        unidad1.ubicar(cuerpoACuerpo);
        unidad2.ubicar(cuerpoACuerpo);
        unidad3.ubicar(rango);
        unidad4.ubicar(asedio);

        // Act
        TierraArrasada tierraArrasada = new TierraArrasada();

        tierraArrasada.usar(tablero);

        Puntaje puntajeObtenido = tablero.calcularPuntaje();

        // Assert
        assertEquals(8+9+10, puntajeObtenido.obtenerValor());
    }
}
