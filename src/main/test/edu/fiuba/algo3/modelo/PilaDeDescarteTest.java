package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.Modificador;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PilaDeDescarteTest {
    @Test

    public void test01AlCrearseUnaPilaEstaSeCreaVacia(){
       //Arrange
        int tamanioEsperado = 0;
        //Act y Assert
        PilaDeDescarte descartes = new PilaDeDescarte();
        //Assert
        Assertions.assertTrue(tamanioEsperado == descartes.cantidadCartas());
    }

    @Test
    public void test02PuedoDescartarUnaCarta(){
        //Arrange
        PilaDeDescarte descartes = new PilaDeDescarte();
        Seccion seccion = new Asedio();
        Modificador modificador = new Modificador();

        Carta carta = new Unidad(seccion, 4, modificador);

        int tamanio = 1;

        //Act
        descartes.descartarUna(carta);

        //Assert
        Assertions.assertTrue(tamanio == descartes.cantidadCartas());

    }
    @Test
    public void test03PuedoDescartasVariasCartas(){
        //Arrange
        PilaDeDescarte descartes = new PilaDeDescarte();

        Rango rango = new Rango();
        Carta carta1 = new Unidad(rango, 2, new Modificador());
        Carta carta2 = new Unidad(rango,4, new Modificador());
        int tamanioDescartes = 2;
        List<Carta> cartas = new ArrayList<>();
        cartas.add(carta1);
        cartas.add(carta2);

        //Act
        descartes.descartarCartas(cartas);

        //Assert
        Assertions.assertTrue(tamanioDescartes == descartes.cantidadCartas());
    }

    @Test
    public void test04PuedoSacarDeLaPilaDeDescarteUnaCartaUsandoCartaMedico(){
        //Arrange
        PilaDeDescarte descartes = new PilaDeDescarte();
        Carta carta1 = new Unidad(new Rango(), 2, new Modificador());
        Carta carta2 = new Unidad(new CuerpoACuerpo(),4, new Modificador());
        int tamanioDescartes = 1;
        List<Carta> cartas = new ArrayList<>();
        cartas.add(carta1);
        cartas.add(carta2);
        descartes.descartarCartas(cartas);

        //Act
        descartes.usarCartaMedico(1);

        //Assert
        Assertions.assertTrue(tamanioDescartes == descartes.cantidadCartas());
    }
}
