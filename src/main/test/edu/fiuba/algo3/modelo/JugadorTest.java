package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;

import edu.fiuba.algo3.modelo.exceptions.NoHayCartasSuficientesException;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasUnidadSuficientesException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class JugadorTest {
    @Test
    public void test01UnJugadorPuedeTomarUnaCartaDelMazo() {
        //"Arrange"
        Mazo mazoUnidades = mock(Mazo.class);
        when(mazoUnidades.obtenerCarta()).thenReturn(new Unidad("Nombre", new CuerpoACuerpo(), 7, new SinModificador()));

        Jugador jugador = new Jugador(new Tablero(new ArrayList<>()), mazoUnidades, new PilaDeDescarte());

        //"Act"
        jugador.tomarCartasMazo(1);

        //"Assert"
        assertEquals(1, jugador.verMano().size());
    }

    @Test
    public void test02UnJugadorPuedeTomarDosCartasDelMazo() {
        //"Arrange"
        Mazo mazoUnidades = mock(Mazo.class);
        when(mazoUnidades.obtenerCarta()).thenReturn(new Unidad("Nombre", new CuerpoACuerpo(), 7, new SinModificador()));

        Jugador jugador = new Jugador(new Tablero(new ArrayList<>()), mazoUnidades, new PilaDeDescarte());

        //"Act"
        jugador.tomarCartasMazo(2);

        //"Assert"
        assertEquals(2, jugador.verMano().size());
    }

    @Test
    public void test03UnJugadorPuedeTomarTresCartasDelMazo() {
        //"Arrange"
        Mazo mazoUnidades = mock(Mazo.class);
        when(mazoUnidades.obtenerCarta()).thenReturn(new Unidad("Nombre", new CuerpoACuerpo(), 7, new SinModificador()));

        Jugador jugador = new Jugador(new Tablero(new ArrayList<>()), mazoUnidades, new PilaDeDescarte());

        //"Act"
        jugador.tomarCartasMazo(3);

        //"Assert"
        assertEquals(3, jugador.verMano().size());
    }

    @Test
    public void test04UnJugadorPuedeSeleccionarUnaUnidad() {
        //"Arrange"
        Mazo mazoUnidades = mock(Mazo.class);
        when(mazoUnidades.obtenerCarta()).thenReturn(new Unidad("Nombre", new CuerpoACuerpo(), 7, new SinModificador()));

        Jugador jugador = new Jugador(new Tablero(new ArrayList<>()), mazoUnidades, new PilaDeDescarte());
        jugador.tomarCartasMazo(1);

        //"Act"
        Carta cartaSeleccionada = jugador.seleccionarCarta(0);

        //"Assert"
        assertEquals(Unidad.class, cartaSeleccionada.getClass());
    }

    @Test
    public void test05UnJugadorPuedeSeleccionarUnaUnidad() {
        //"Arrange"
        Mazo mazoUnidades = mock(Mazo.class);
        when(mazoUnidades.obtenerCarta()).thenReturn(new Unidad("Nombre", new CuerpoACuerpo(), 7, new SinModificador()));

        Jugador jugador = new Jugador(new Tablero(new ArrayList<>()), mazoUnidades, new PilaDeDescarte());
        jugador.tomarCartasMazo(2);

        //"Act"
        Carta cartaSeleccionada = jugador.seleccionarCarta(1);

        //"Assert"
        assertEquals(Unidad.class, cartaSeleccionada.getClass());
    }

    @Test
    public void test06UnJugadorPuedeObtenerSuPuntaje() {
        //"Arrange"
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);

        Jugador jugador = new Jugador(new Tablero(secciones), mock(Mazo.class), new PilaDeDescarte());

        new Unidad("Nombre", cuerpoACuerpo, 4, new SinModificador()).ubicar(cuerpoACuerpo);
        new Unidad("Nombre", cuerpoACuerpo, 2, new SinModificador()).ubicar(cuerpoACuerpo);

        //"Act"
        Puntaje puntajeObtenido = jugador.obtenerPuntaje();

        //"Assert"
        assertEquals(6, puntajeObtenido.obtenerValor());
    }

    @Test
    public void test07UnJugadorPuedeObtenerSuPuntaje() {
        //"Arrange"
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);

        Jugador jugador = new Jugador(new Tablero(secciones), mock(Mazo.class), new PilaDeDescarte());

        new Unidad("Nombre", cuerpoACuerpo, 7, new SinModificador()).ubicar(cuerpoACuerpo);
        new Unidad("Nombre", cuerpoACuerpo, 8, new SinModificador()).ubicar(cuerpoACuerpo);

        //"Act"
        Puntaje puntajeObtenido = jugador.obtenerPuntaje();

        //"Assert"
        assertEquals(15, puntajeObtenido.obtenerValor());
    }

    @Test
    public void test08UnJugadorPuedeVerificarSiTieneCartasSuficientesEnSuMano() {
        //"Arrange"
        Mazo mazoUnidades = mock(Mazo.class);
        when(mazoUnidades.obtenerCarta()).thenReturn(new Unidad("Nombre", new CuerpoACuerpo(), 7, new SinModificador()));

        Jugador jugador = new Jugador(new Tablero(new ArrayList<>()), mazoUnidades, new PilaDeDescarte());

        jugador.tomarCartasMazo(5);

        //Act y Assert
        jugador.validarCartasSuficientesMano(5);
    }

    @Test
    public void test09UnJugadorPuedeVerificarSiTieneCartasSuficientesEnSuMano() {
        //"Arrange"
        Mazo mazoUnidades = mock(Mazo.class);
        when(mazoUnidades.obtenerCarta()).thenReturn(new Unidad("Nombre", new CuerpoACuerpo(), 7, new SinModificador()));

        Jugador jugador = new Jugador(new Tablero(new ArrayList<>()), mazoUnidades, new PilaDeDescarte());

        jugador.tomarCartasMazo(9);

        //Act y Assert
        jugador.validarCartasSuficientesMano(7);
    }

    @Test
    public void test10UnJugadorPuedeVerificarSiNoTieneCartasSuficientesEnSuMano() {
        //"Arrange"
        Mazo mazoUnidades = mock(Mazo.class);
        when(mazoUnidades.obtenerCarta()).thenReturn(new Unidad("Nombre", new CuerpoACuerpo(), 7, new SinModificador()));

        Jugador jugador = new Jugador(new Tablero(new ArrayList<>()), mazoUnidades, new PilaDeDescarte());

        jugador.tomarCartasMazo(1);

        //Act y Assert
        assertThrows(
                NoHayCartasSuficientesException.class,
                ()-> jugador.validarCartasSuficientesMano(5)
        );
    }

    @Test
    public void test11UnJugadorPuedeVerificarSiNoTieneCartasSuficientesEnSuMano() {
        //"Arrange"
        Mazo mazoUnidades = mock(Mazo.class);
        when(mazoUnidades.obtenerCarta()).thenReturn(new Unidad("Nombre", new CuerpoACuerpo(), 7, new SinModificador()));

        Jugador jugador = new Jugador(new Tablero(new ArrayList<>()), mazoUnidades, new PilaDeDescarte());

        jugador.tomarCartasMazo(13);

        //Act y Assert
        assertThrows(
                NoHayCartasSuficientesException.class,
                ()-> jugador.validarCartasSuficientesMano(14)
        );
    }
}
