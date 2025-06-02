package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasEspecialesSuficientesException;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasSuficientesException;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasUnidadSuficientesException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class testEntrega1 {
    //Punto 1
    @Test
    public void test01UnJugadorPoseeCartasSuficientesEnSuMazoParaEmpezarElJuego() {
        //Inicial faseActual = new Inicial();
        Seccion seccionCuerpoACuerpo = new Seccion();
        Seccion secconRango = new Seccion();
        Seccion seccionAsedio = new Seccion();
        Tablero tablero = new Tablero(seccionCuerpoACuerpo, secconRango, seccionAsedio);

        List<Unidad> unidades = new ArrayList<Unidad>();
        List<Especial> especiales = new ArrayList<Especial>();

        for (int i = 0; i < 15; i++) {
            unidades.add(new Unidad(seccionCuerpoACuerpo, 4, new Modificador()));
        }
        for (int i = 0; i < 6; i++) {
            especiales.add(new Especial());
        }

        Mazo mazo = new Mazo(unidades, especiales);
        Jugador jugador = new Jugador(tablero, mazo);

        jugador.validarCartasSuficientesMazo(15, 6);
    }

    @Test
    public void test02UnJugadorNoPoseeUnidadesSuficientesEnSuMazoParaEmpezarElJuego() {
        //Inicial faseActual = new Inicial();
        Seccion seccionCuerpoACuerpo = new Seccion();
        Seccion secconRango = new Seccion();
        Seccion seccionAsedio = new Seccion();
        Tablero tablero = new Tablero(seccionCuerpoACuerpo, secconRango, seccionAsedio);

        List<Unidad> unidades = new ArrayList<Unidad>();
        List<Especial> especiales = new ArrayList<Especial>();

        for (int i = 0; i < 14; i++) {
            unidades.add(new Unidad(seccionCuerpoACuerpo, 4, new Modificador()));
        }
        for (int i = 0; i < 6; i++) {
            especiales.add(new Especial());
        }

        Mazo mazo = new Mazo(unidades, especiales);
        Jugador jugador = new Jugador(tablero, mazo);

        assertThrows(
                NoHayCartasUnidadSuficientesException.class,
                ()-> jugador.validarCartasSuficientesMazo(15, 6)
        );
    }

    @Test
    public void test03UnJugadorNoPoseeEspecialesSuficientesEnSuMazoParaEmpezarElJuego() {
        //Inicial faseActual = new Inicial();
        Seccion seccionCuerpoACuerpo = new Seccion();
        Seccion secconRango = new Seccion();
        Seccion seccionAsedio = new Seccion();
        Tablero tablero = new Tablero(seccionCuerpoACuerpo, secconRango, seccionAsedio);

        List<Unidad> unidades = new ArrayList<Unidad>();
        List<Especial> especiales = new ArrayList<Especial>();

        for (int i = 0; i < 15; i++) {
            unidades.add(new Unidad(seccionCuerpoACuerpo, 4, new Modificador()));
        }
        for (int i = 0; i < 5; i++) {
            especiales.add(new Especial());
        }

        Mazo mazo = new Mazo(unidades, especiales);
        Jugador jugador = new Jugador(tablero, mazo);

        assertThrows(
                NoHayCartasEspecialesSuficientesException.class,
                ()-> jugador.validarCartasSuficientesMazo(15, 6)
        );
    }
// Punto 2
    @Test
    public void test04UnJugadorPoseeCartasSuficientesEnSuManoParaEmpezarElJuego() {
        //Inicial faseActual = new Preparacion();
        Seccion seccionCuerpoACuerpo = new Seccion();
        Seccion secconRango = new Seccion();
        Seccion seccionAsedio = new Seccion();
        Tablero tablero = new Tablero(seccionCuerpoACuerpo, secconRango, seccionAsedio);

        List<Unidad> unidades = new ArrayList<Unidad>();
        List<Especial> especiales = new ArrayList<Especial>();

        for (int i = 0; i < 15; i++) {
            unidades.add(new Unidad(seccionCuerpoACuerpo, 4, new Modificador()));
        }
        for (int i = 0; i < 5; i++) {
            especiales.add(new Especial());
        }

        Mazo mazo = new Mazo(unidades, especiales);
        Jugador jugador = new Jugador(tablero, mazo);
        jugador.tomarCartasMazo(10);

        jugador.validarCartasSuficientesMano(10);
    }

    @Test
    public void test05UnJugadorNoPoseeCartasSuficientesEnSuManoParaEmpezarElJuego() {
        //Inicial faseActual = new Preparacion();
        Seccion seccionCuerpoACuerpo = new Seccion();
        Seccion secconRango = new Seccion();
        Seccion seccionAsedio = new Seccion();
        Tablero tablero = new Tablero(seccionCuerpoACuerpo, secconRango, seccionAsedio);

        List<Unidad> unidades = new ArrayList<Unidad>();
        List<Especial> especiales = new ArrayList<Especial>();

        for (int i = 0; i < 15; i++) {
            unidades.add(new Unidad(seccionCuerpoACuerpo, 4, new Modificador()));
        }
        for (int i = 0; i < 5; i++) {
            especiales.add(new Especial());
        }

        Mazo mazo = new Mazo(unidades, especiales);
        Jugador jugador = new Jugador(tablero, mazo);
        jugador.tomarCartasMazo(9);

        assertThrows(
                NoHayCartasSuficientesException.class,
                ()-> jugador.validarCartasSuficientesMano(10)
        );
    }
    // Punto 3

    @Test
    public void test06UnJugadorColocaCorrectamenteUnaUnidadEnElTablero() {
        Seccion seccionCuerpoACuerpo = new Seccion();
        Seccion secconRango = new Seccion();
        Seccion seccionAsedio = new Seccion();
        Tablero tablero = new Tablero(seccionCuerpoACuerpo, secconRango, seccionAsedio);

        List<Unidad> unidades = new ArrayList<Unidad>();
        List<Especial> especiales = new ArrayList<Especial>();

        for (int i = 0; i < 15; i++) {
            unidades.add(new Unidad(seccionCuerpoACuerpo, 4, new Modificador()));
        }
        for (int i = 0; i < 6; i++) {
            especiales.add(new Especial());
        }

        Mazo mazo = new Mazo(unidades, especiales);
        Jugador jugador = new Jugador(tablero, mazo);
        jugador.tomarCartasMazo(10);
        Carta cartaSeleccionada = jugador.seleccionarCarta(2);
        cartaSeleccionada.usar(tablero);

    }
    // Punto 4
    @Test
    public void test07UnJugadorColocaCorrectamenteUnaUnidadEnElTableroYSeCalculaElPuntaje() {
        Seccion seccionCuerpoACuerpo = new Seccion();
        Seccion secconRango = new Seccion();
        Seccion seccionAsedio = new Seccion();
        Tablero tablero = new Tablero(seccionCuerpoACuerpo, secconRango, seccionAsedio);

        List<Unidad> unidades = new ArrayList<Unidad>();
        List<Especial> especiales = new ArrayList<Especial>();

        for (int i = 0; i < 15; i++) {
            unidades.add(new Unidad(seccionCuerpoACuerpo, 4, new Modificador()));
        }
        for (int i = 0; i < 6; i++) {
            especiales.add(new Especial());
        }

        Mazo mazo = new Mazo(unidades, especiales);
        Jugador jugador = new Jugador(tablero, mazo);
        jugador.tomarCartasMazo(10);
        Carta cartaSeleccionada = jugador.seleccionarCarta(2);
        cartaSeleccionada.usar(tablero);

        //Puntaje puntaje = jugador.obtenerPuntaje();

    }


    // Correspondiente al punto 6 - Falta el "que solo se aplique el valor a la ronda"

    @Test
    public void test08ModificoUnaCartaConUnaCartaaUnidaYSeCambianLosPuntos() {

        /*
        Primera idea de implementación. La otra forma seria que cada carta tenga su propio
        modificador, pero tiene que haber un metodo que le pregunte a sección la cantidad
        de cartas del mismo tipo que hay para así multiplicar
        */

        Seccion seccionCuerpoACuerpo = new Seccion();
        Seccion seccionRango = new Seccion();
        Seccion seccionAsedio = new Seccion();
        Tablero tablero = new Tablero(seccionCuerpoACuerpo, seccionRango, seccionAsedio);

        Unida modificador = new Unida(1);
        Unidad catapulta1 = new Unidad(seccionAsedio, 8, modificador);
        Unidad catapulta2 = new Unidad(seccionAsedio, 8, modificador);

        catapulta1.usar(tablero);
        catapulta2.usar(tablero);
        modificador.sumarCantidadEnSeccion();

        Puntaje puntajeTotal = seccionAsedio.puntajeTotal();

        Assertions.assertTrue(puntajeTotal.equals(32));
    }
}
