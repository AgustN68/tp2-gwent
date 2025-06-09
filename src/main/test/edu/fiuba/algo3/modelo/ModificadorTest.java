package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.Modificador;
import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Modificador.Unida;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ModificadorTest {

    @Test
    public void test01unModificadorNormalCalculaElPuntajeCorrecto() {


        // Arrange
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Tablero tablero = new Tablero(cuerpoACuerpo, new Asedio(), new Rango());
        CuerpoACuerpo seccionCuerpo = new CuerpoACuerpo();
        Modificador normal = new SinModificador();
        Unidad unidad = new Unidad(seccionCuerpo, 5,normal);

        // Act
        unidad.usar();
        Puntaje puntajeObtenido = unidad.calcularPuntaje();

        // Assert
        Assertions.assertTrue(puntajeObtenido.equals(5));
    }



    @Test
    public void test02unModificadorUnidaCalculaElPuntajeCorrectoConUnaCartaPuesta() {

        //Arrange
        Seccion seccionCuerpoACuerpo = new CuerpoACuerpo();
        Seccion seccionRango = new Rango();
        Seccion seccionAsedio = new Asedio();
        Tablero tablero = new Tablero(seccionCuerpoACuerpo, seccionRango, seccionAsedio);

        Unida modificador = new Unida(0);
        Unidad catapulta1 = new Unidad(seccionAsedio, 8, modificador);


        //Act
        catapulta1.usar();
        modificador.sumarCantidadEnSeccion();

        Puntaje puntajeTotal = seccionAsedio.puntajeTotal();

        //Assert
        Assertions.assertTrue(puntajeTotal.equals(8));


    }
    @Test
    public void test03unModificadorUnidaCalculaElPuntajeCorrectoConTresCartasPuestas() {

        // Arrange
        Seccion seccionCuerpoACuerpo = new CuerpoACuerpo();
        Seccion seccionRango = new Rango();
        Seccion seccionAsedio = new Asedio();

        Tablero tablero = new Tablero(seccionCuerpoACuerpo, seccionRango, seccionAsedio);

        Unida modificador = new Unida(0);
        Unidad catapulta1 = new Unidad(seccionAsedio, 8, modificador);
        Unidad catapulta2 = new Unidad(seccionAsedio, 8, modificador);
        Unidad catapulta3 = new Unidad(seccionAsedio, 8, modificador);

        // Act
        catapulta1.usar();
        modificador.sumarCantidadEnSeccion();

        catapulta2.usar();
        modificador.sumarCantidadEnSeccion();

        catapulta3.usar();
        modificador.sumarCantidadEnSeccion();


        Puntaje puntajeTotal = seccionAsedio.puntajeTotal();

        // Assert
        Assertions.assertTrue(puntajeTotal.equals(72));


    }

}
