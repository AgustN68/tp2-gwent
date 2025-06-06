package edu.fiuba.algo3.modelo.CartaTest;

import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.Modificador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UnidadTest {

    @Test
    public void test01SeColocaCorrectamenteUnaUnidadEnElTablero(){
        // Arrange
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Unidad unidad = new Unidad(cuerpoACuerpo, 5, new SinModificador());

        // Act y Assert
        unidad.ubicar(cuerpoACuerpo);

    }

    @Test
    public void test02SeCalculaElPuntajeDeUnaUnidad(){
        // Arrange
        Unidad unidad = new Unidad(new Asedio(), 5, new SinModificador());

        // Act
        Puntaje puntajeObtenido = unidad.calcularPuntaje();

        // Assert
        Assertions.assertTrue(puntajeObtenido.equals(5));
    }

    @Test
    public void test03SeCalculaElPuntajeDeOtraUnidad(){
        // Arrange
        Unidad unidad = new Unidad(new CuerpoACuerpo(), 8, new SinModificador());

        // Act
        Puntaje puntajeObtenido = unidad.calcularPuntaje();

        // Assert
        Assertions.assertTrue(puntajeObtenido.equals(8));
    }
}
