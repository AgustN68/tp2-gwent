package edu.fiuba.algo3.modelo.CartaTest;

import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Unidad;
import edu.fiuba.algo3.modelo.Modificador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UnidadTest {

    @Test
    public void test01SeColocaCorrectamenteUnaUnidadEnElTablero(){
        // Arrange
        Seccion cuerpoACuerpo = new Seccion();
        Unidad unidad = new Unidad(cuerpoACuerpo, 5, new Modificador());

        // Act y Assert
        unidad.ubicar(new Tablero(cuerpoACuerpo, new Seccion(), new Seccion()));

    }

    @Test
    public void test02SeCalculaElPuntajeDeUnaUnidad(){
        // Arrange
        Seccion asedio = new Seccion();
        Unidad unidad = new Unidad(asedio, 5, new Modificador());

        // Act
        Puntaje puntajeObtenido = unidad.calcularPuntaje();

        // Assert
        Assertions.assertTrue(puntajeObtenido.equals(5));
    }

    @Test
    public void test03SeCalculaElPuntajeDeOtraUnidad(){
        // Arrange
        Seccion cuerpoACuerpo = new Seccion();
        Unidad unidad = new Unidad(cuerpoACuerpo, 8, new Modificador());

        // Act
        Puntaje puntajeObtenido = unidad.calcularPuntaje();

        // Assert
        Assertions.assertTrue(puntajeObtenido.equals(8));
    }
}
