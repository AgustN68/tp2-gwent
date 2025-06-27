package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PuntajeTest {

    @Test
    public void test01creoUnPuntajeConUnValorYEsElCorrecto() {

        // Arrange

        Puntaje puntaje = new Puntaje(50);

        // Act
        Boolean resultado = puntaje.equals(50);

        // Assert
        assertTrue(resultado);

    }


    @Test
    public void test02PuedoSumarDosPuntajesCorrectamente() {

        // Arrange
        Puntaje puntaje1 = new Puntaje(50);
        Puntaje puntaje2 = new Puntaje(150);


        // Act
        Puntaje nuevoPuntaje = puntaje1.sumarPuntaje(puntaje2);

        // Assert
        assertTrue(nuevoPuntaje.equals(200));

    }

    @Test
    public void test03PuedoRestarleUnPuntajeAOtroPuntajeCorrectamente() {

        // Arrange
        Puntaje puntaje1 = new Puntaje(100);
        Puntaje puntaje2 = new Puntaje(101);


        // Act
        Puntaje nuevoPuntaje = puntaje2.restarPuntaje(puntaje1);

        // Assert
        assertTrue(nuevoPuntaje.equals(1));

    }

    @Test
    public void test04PuedoActualizarElValorAUnPuntajeCorrectamente() {

        // Arrange
        Puntaje puntaje1 = new Puntaje(100);

        // Act
        puntaje1.modificarValor(50);

        // Assert
        assertTrue(puntaje1.equals(50));

    }

    @Test
    public void test05UnPuntajePuedeVerificarSiTieneElMismoValorQueOtroPuntaje() {

        // Arrange
        Puntaje puntaje1 = new Puntaje(100);
        Puntaje puntaje2 = new Puntaje(100);

        // Act
        Boolean resultado = puntaje1.equalsPuntaje(puntaje2);

        // Assert
        assertTrue(resultado);

    }

    @Test
    public void test06UnPuntajePuedeVerificarSiNoTieneElMismoValorQueOtroPuntaje() {

        // Arrange
        Puntaje puntaje1 = new Puntaje(50);
        Puntaje puntaje2 = new Puntaje(60);

        // Act
        Boolean resultado = puntaje1.equalsPuntaje(puntaje2);

        // Assert
        assertFalse(resultado);

    }

    @Test
    public void test07creoUnPuntajeConUnValorNegativoYDebeDarError() {

        assertThrows(IllegalArgumentException.class, () -> {new Puntaje(-10);});
    }

}
