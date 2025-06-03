package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.colocadores.Colocador;
import edu.fiuba.algo3.modelo.colocadores.ColocadorAsedio;
import edu.fiuba.algo3.modelo.colocadores.ColocadorCuerpoACuerpo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ModificadorTest {

    @Test
    public void test01unModificadorNormalCalculaElPuntajeCorrecto() {


        // Arrange
        Seccion cuerpoACuerpo = new Seccion();
        Tablero tablero = new Tablero(cuerpoACuerpo, new Seccion(), new Seccion());
        Colocador colocadorCuerpo = new ColocadorCuerpoACuerpo();
        Modificador normal = new Modificador();
        Unidad unidad = new Unidad(colocadorCuerpo, 5,normal);

        // Act
        unidad.usar(tablero);
        Puntaje puntajeObtenido = unidad.calcularPuntaje();

        // Assert
        Assertions.assertTrue(puntajeObtenido.equals(5));
    }



    @Test
    public void test02unModificadorUnidaCalculaElPuntajeCorrectoConUnaCartaPuesta() {

        //Arrange
        Seccion seccionCuerpoACuerpo = new Seccion();
        Seccion seccionRango = new Seccion();
        Seccion seccionAsedio = new Seccion();
        Tablero tablero = new Tablero(seccionCuerpoACuerpo, seccionRango, seccionAsedio);

        ColocadorAsedio colocadorAsedio = new ColocadorAsedio();

        Unida modificador = new Unida(0);
        Unidad catapulta1 = new Unidad(colocadorAsedio, 8, modificador);


        //Act
        catapulta1.usar(tablero);
        modificador.sumarCantidadEnSeccion();

        Puntaje puntajeTotal = seccionAsedio.puntajeTotal();

        //Assert
        Assertions.assertTrue(puntajeTotal.equals(8));


    }
    @Test
    public void test03unModificadorUnidaCalculaElPuntajeCorrectoConTresCartasPuestas() {

        // Arrange
        Seccion seccionCuerpoACuerpo = new Seccion();
        Seccion seccionRango = new Seccion();
        Seccion seccionAsedio = new Seccion();

        Tablero tablero = new Tablero(seccionCuerpoACuerpo, seccionRango, seccionAsedio);

        ColocadorAsedio colocadorAsedio = new ColocadorAsedio();

        Unida modificador = new Unida(0);
        Unidad catapulta1 = new Unidad(colocadorAsedio, 8, modificador);
        Unidad catapulta2 = new Unidad(colocadorAsedio, 8, modificador);
        Unidad catapulta3 = new Unidad(colocadorAsedio, 8, modificador);

        // Act
        catapulta1.usar(tablero);
        modificador.sumarCantidadEnSeccion();

        catapulta2.usar(tablero);
        modificador.sumarCantidadEnSeccion();

        catapulta3.usar(tablero);
        modificador.sumarCantidadEnSeccion();


        Puntaje puntajeTotal = seccionAsedio.puntajeTotal();

        // Assert
        Assertions.assertTrue(puntajeTotal.equals(72));


    }

}
