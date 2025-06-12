package edu.fiuba.algo3.modelo.CartaTest;

import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
        assertTrue(puntajeObtenido.equals(5));
    }

    @Test
    public void test03SeCalculaElPuntajeDeOtraUnidad(){
        // Arrange
        Unidad unidad = new Unidad(new CuerpoACuerpo(), 8, new SinModificador());

        // Act
        Puntaje puntajeObtenido = unidad.calcularPuntaje();

        // Assert
        assertTrue(puntajeObtenido.equals(8));
    }

    @Test
    public void test04UnaUnidadPuedeVerificarSiSuPuntajeEsIgualAOtroPuntaje(){
        // Arrange
        Unidad unidad = new Unidad(new CuerpoACuerpo(), 8, new SinModificador());
        Puntaje puntaje = new Puntaje(8);

        // Act
        Boolean resutlado = unidad.tienePuntaje(puntaje);

        // Assert
        assertTrue(resutlado);
    }

    @Test
    public void test05UnaUnidadPuedeVerificarSiSuPuntajeNoEsIgualAOtroPuntaje(){
        // Arrange
        Unidad unidad = new Unidad(new CuerpoACuerpo(), 10, new SinModificador());
        Puntaje puntaje = new Puntaje(5);

        // Act
        Boolean resutlado = unidad.tienePuntaje(puntaje);

        // Assert
        assertFalse(resutlado);
    }
}
