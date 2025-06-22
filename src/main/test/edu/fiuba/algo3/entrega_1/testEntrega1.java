package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima.Clima;
import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.Modificador;
import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Modificador.Unida;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasEspecialesSuficientesException;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasSuficientesException;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasUnidadSuficientesException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class testEntrega1 {
    //Punto 1
    @Test
    public void test01UnJugadorPoseeCartasSuficientesEnSuMazoParaEmpezarElJuego() {
        //Inicial faseActual = new Inicial();
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);

        Tablero tablero = new Tablero(secciones);

        List<Unidad> unidades = new ArrayList<Unidad>();
        List<Especial> especiales = new ArrayList<Especial>();

        for (int i = 0; i < 15; i++) {
            unidades.add(new Unidad("Nombre", cuerpoACuerpo, 4, new SinModificador()));
        }
        for (int i = 0; i < 6; i++) {

            List<Seccion> seccionesAfectadas = new ArrayList<>();
            seccionesAfectadas.add(asedio);
            seccionesAfectadas.add(new Asedio());

            especiales.add(new Clima(seccionesAfectadas));
        }

        Mazo mazo = new Mazo(unidades, especiales);
        Jugador jugador = new Jugador(tablero, mazo, new PilaDeDescarte());

        //jugador.validarCartasSuficientesMazo(15, 6);
    }

    @Test
    public void test02UnJugadorNoPoseeUnidadesSuficientesEnSuMazoParaEmpezarElJuego() {
        //Inicial faseActual = new Inicial();
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);

        Tablero tablero = new Tablero(secciones);

        List<Unidad> unidades = new ArrayList<Unidad>();
        List<Especial> especiales = new ArrayList<Especial>();

        for (int i = 0; i < 14; i++) {
            unidades.add(new Unidad("Nombre", cuerpoACuerpo, 4, new SinModificador()));
        }
        for (int i = 0; i < 6; i++) {
            List<Seccion> seccionesAfectadas = new ArrayList<>();
            seccionesAfectadas.add(asedio);
            seccionesAfectadas.add(new Asedio());

            especiales.add(new Clima(seccionesAfectadas));
        }

        assertThrows(
                NoHayCartasUnidadSuficientesException.class,
                ()-> new Mazo(unidades, especiales)
        );
    }

    @Test
    public void test03UnJugadorNoPoseeEspecialesSuficientesEnSuMazoParaEmpezarElJuego() {
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);

        Tablero tablero = new Tablero(secciones);

        List<Unidad> unidades = new ArrayList<Unidad>();
        List<Especial> especiales = new ArrayList<Especial>();

        for (int i = 0; i < 15; i++) {
            unidades.add(new Unidad("Nombre", cuerpoACuerpo, 4, new SinModificador()));
        }
        for (int i = 0; i < 5; i++) {
            List<Seccion> seccionesAfectadas = new ArrayList<>();
            seccionesAfectadas.add(asedio);
            seccionesAfectadas.add(new Asedio());

            especiales.add(new Clima(seccionesAfectadas));
        }


        assertThrows(
                NoHayCartasEspecialesSuficientesException.class,
                ()-> new Mazo(unidades, especiales)
        );
    }
// Punto 2
    @Test
    public void test04UnJugadorPoseeCartasSuficientesEnSuManoParaEmpezarElJuego() {
        //Inicial faseActual = new Preparacion();
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);

        Tablero tablero = new Tablero(secciones);

        List<Unidad> unidades = new ArrayList<Unidad>();
        List<Especial> especiales = new ArrayList<Especial>();

        for (int i = 0; i < 15; i++) {
            unidades.add(new Unidad("Nombre", cuerpoACuerpo, 4, new SinModificador()));
        }
        for (int i = 0; i < 6; i++) {
            List<Seccion> seccionesAfectadas = new ArrayList<>();
            seccionesAfectadas.add(asedio);
            seccionesAfectadas.add(asedio);

            especiales.add(new Clima(seccionesAfectadas));
        }

        Mazo mazo = new Mazo(unidades, especiales);
        Jugador jugador = new Jugador(tablero, mazo, new PilaDeDescarte());
        jugador.tomarCartasMazo(10);

        jugador.validarCartasSuficientesMano(10);
    }
//test 02 caso negativo
    @Test
    public void test05UnJugadorNoPoseeCartasSuficientesEnSuManoParaEmpezarElJuego() {
        //Inicial faseActual = new Preparacion();
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);

        Tablero tablero = new Tablero(secciones);

        List<Unidad> unidades = new ArrayList<Unidad>();
        List<Especial> especiales = new ArrayList<Especial>();

        for (int i = 0; i < 15; i++) {
            unidades.add(new Unidad("Nombre", cuerpoACuerpo, 4, new SinModificador()));
        }
        for (int i = 0; i < 6; i++) {
            List<Seccion> seccionesAfectadas = new ArrayList<>();
            seccionesAfectadas.add(asedio);
            seccionesAfectadas.add(asedio);

            especiales.add(new Clima(seccionesAfectadas));
        }

        Mazo mazo = new Mazo(unidades, especiales);
        Jugador jugador = new Jugador(tablero, mazo, new PilaDeDescarte());
        jugador.tomarCartasMazo(9);

        assertThrows(
                NoHayCartasSuficientesException.class,
                ()-> jugador.validarCartasSuficientesMano(10)
        );
    }
    // Punto 3

    @Test
    public void test06UnJugadorColocaCorrectamenteUnaUnidadEnElTablero() {
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);

        Tablero tablero = new Tablero(secciones);

        Unidad unidad = new Unidad("Nombre", cuerpoACuerpo, 4, new SinModificador());
        Mazo mockedMazo = mock(Mazo.class);

        when(mockedMazo.obtenerCarta()).thenReturn(unidad);

        Jugador jugador = new Jugador(tablero, mockedMazo, new PilaDeDescarte());
        jugador.tomarCartasMazo(1);
        Carta cartaSeleccionada = jugador.seleccionarCarta(0);
        cartaSeleccionada.usar();

    }
    // Punto 4
    @Test
    public void test07UnJugadorColocaCorrectamenteUnaUnidadEnElTableroYSeCalculaElPuntaje() {
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);

        Tablero tablero = new Tablero(secciones);

        Mazo mockedMazo = mock(Mazo.class);

        Unidad unidad = new Unidad("Nombre", cuerpoACuerpo, 4, new SinModificador());

        when(mockedMazo.obtenerCarta()).thenReturn(unidad);

        Jugador jugador = new Jugador(tablero, mockedMazo, new PilaDeDescarte());
        jugador.tomarCartasMazo(1);
        Carta cartaSeleccionada = jugador.seleccionarCarta(0);
        cartaSeleccionada.usar();

        Puntaje puntaje = jugador.obtenerPuntaje();

        Assertions.assertTrue(puntaje.equals(4));

    }
    // Punto 5
    @Test
    public void test08PuedoDescartarUnaCarta() {
        //Arrange
        PilaDeDescarte descartes = new PilaDeDescarte();
        Modificador modificador = new SinModificador();
        Rango seccion = new Rango();

        Carta carta = new Unidad("Nombre", seccion, 4, modificador);

        int tamanio = 1;

        //Act
        descartes.descartarUna(carta);

        //Assert
        Assertions.assertEquals(tamanio, descartes.cantidadCartas());
    }


    @Test
    public void test09ModificoUnaCartaConUnaCartaaUnidaYSeCambianLosPuntos() {

        // Arrange
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);

        Tablero tablero = new Tablero(secciones);

        Unida modificador1 = new Unida();
        Unida modificador2 = new Unida();

        Unidad catapulta1 = new Unidad("Catapulta", asedio, 8, modificador1);
        Unidad catapulta2 = new Unidad("Catapulta", asedio, 8, modificador2);

        modificador1.setCarta(catapulta1);
        modificador2.setCarta( catapulta2);

        catapulta1.usar();
        catapulta2.usar();

        Puntaje puntajeTotal = asedio.puntajeTotal();

        Assertions.assertTrue(puntajeTotal.equals(32));
    }

    // Punto 7
    @Test
    public void test10SeAplicaElEfectoDelClimaYSeReduceElValorDeLasCartasDeLaSeccionCorrespondiente(){
        Seccion cuerpoACuerpo1 = new CuerpoACuerpo();
        Seccion cuerpoACuerpo2 = new CuerpoACuerpo();
        Unidad unidad1 = new Unidad("Nombre", cuerpoACuerpo1,5,new SinModificador());
        Unidad unidad2 = new Unidad("Nombre", cuerpoACuerpo2,6,new SinModificador());

        List<Seccion> seccionesAfectadas = new ArrayList<>();
        seccionesAfectadas.add(cuerpoACuerpo1);
        seccionesAfectadas.add(cuerpoACuerpo2);

        Clima nieve = new Clima(seccionesAfectadas);

        cuerpoACuerpo1.ubicar(unidad1);
        cuerpoACuerpo2.ubicar(unidad2);

        nieve.usar();
        Puntaje puntajeSeccion1 = cuerpoACuerpo1.puntajeTotal();
        Puntaje puntajeSeccion2 = cuerpoACuerpo2.puntajeTotal();

        Assertions.assertTrue(puntajeSeccion1.equals(1) && puntajeSeccion2.equals(1));
    }
}
