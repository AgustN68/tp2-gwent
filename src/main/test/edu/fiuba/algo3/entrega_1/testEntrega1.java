package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasEspecialesSuficientesException;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasSuficientesException;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasUnidadSuficientesException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class testEntrega1 {

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
            unidades.add(new Unidad(seccionCuerpoACuerpo, 4));
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
            unidades.add(new Unidad(seccionCuerpoACuerpo, 4));
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
            unidades.add(new Unidad(seccionCuerpoACuerpo, 4));
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
            unidades.add(new Unidad(seccionCuerpoACuerpo, 4));
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
            unidades.add(new Unidad(seccionCuerpoACuerpo, 4));
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
}
