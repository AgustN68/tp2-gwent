package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Carta.Especial.Clima.Clima;
import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Carta.Especial.Limpiar;
import edu.fiuba.algo3.modelo.Carta.Especial.TierraArrasada;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mazo;
import edu.fiuba.algo3.modelo.Modificador.Agil;
import edu.fiuba.algo3.modelo.Modificador.Espia;
import edu.fiuba.algo3.modelo.Modificador.Modificador;
import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Tablero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;

public class testEntrega2 {
    @Test
    public void test01SePuedeEliminarEfectoClima(){
        Seccion cuerpoACuerpo1 = new CuerpoACuerpo();
        Seccion cuerpoACuerpo2 = new CuerpoACuerpo();
        Tablero tablero1 = new Tablero(cuerpoACuerpo1,new Rango(), new Asedio());
        Tablero tablero2 = new Tablero(cuerpoACuerpo2, new Rango(), new Asedio());
        int puntosCarta1 = 5;
        int puntosCarta2 = 6;
        Unidad unidad1 = new Unidad(cuerpoACuerpo1,puntosCarta1,new SinModificador());
        Unidad unidad2 = new Unidad(cuerpoACuerpo2,puntosCarta2,new SinModificador());
        Clima nieve = new Clima(cuerpoACuerpo1, cuerpoACuerpo2);
        Limpiar limpiarClima = new Limpiar(tablero1, tablero2);

        cuerpoACuerpo1.ubicar(unidad1);
        cuerpoACuerpo2.ubicar(unidad2);

        nieve.usar();

        limpiarClima.usar();

        Puntaje puntajeSeccion1 = cuerpoACuerpo1.puntajeTotal();
        Puntaje puntajeSeccion2 = cuerpoACuerpo2.puntajeTotal();

        Assertions.assertTrue(puntajeSeccion1.equals(puntosCarta1) && puntajeSeccion2.equals(puntosCarta2));

    }
    @Test
    public void test02SeUsaTierraArrasadaYSeEliminanLasCartasCorrectamente() {
        // Arrange
        CuerpoACuerpo cuerpoACuerpo1 = new CuerpoACuerpo();
        Rango rango1 = new Rango();
        Asedio asedio1 = new Asedio();
        CuerpoACuerpo cuerpoACuerpo2 = new CuerpoACuerpo();
        Rango rango2 = new Rango();
        Asedio asedio2 = new Asedio();

        Tablero tablero1 = new Tablero(cuerpoACuerpo1, rango1, asedio1);
        Tablero tablero2 = new Tablero(cuerpoACuerpo2, rango2, asedio2);

        Unidad unidad1 = new Unidad(cuerpoACuerpo1, 8, new SinModificador());
        Unidad unidad2 = new Unidad(cuerpoACuerpo2, 9, new SinModificador());
        Unidad unidad3 = new Unidad(rango2, 10, new SinModificador());

        unidad1.ubicar(cuerpoACuerpo1);
        unidad2.ubicar(cuerpoACuerpo2);
        unidad3.ubicar(rango2);

        // Act
        TierraArrasada tierraArrasada = new TierraArrasada(tablero2);

        tierraArrasada.usar();

        Puntaje puntajeObtenido = tablero2.calcularPuntaje();

        // Assert
        Assertions.assertTrue(puntajeObtenido.equals(9));
    }

    @Test
    public void test03UnaCartaEspiaSeColocaEnElTablero2YTePermiteAgarrar2Cartas() {
        // Arrange

        CuerpoACuerpo cuerpoACuerpo1 = new CuerpoACuerpo();
        Rango rango1 = new Rango();
        Asedio asedio1 = new Asedio();

        CuerpoACuerpo cuerpoACuerpo2 = new CuerpoACuerpo();
        Rango rango2 = new Rango();
        Asedio asedio2 = new Asedio();


        Tablero tablero1 = new Tablero(cuerpoACuerpo1, rango1, asedio1);
        Tablero tablero2 = new Tablero(cuerpoACuerpo2, rango2, asedio2);


        List<Unidad> unidades = new ArrayList<>();
        List<Especial> especiales = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            unidades.add(new Unidad(cuerpoACuerpo1, 2, new SinModificador()));
        }

        for (int i = 0; i < 6; i++) {
            especiales.add(new Clima(cuerpoACuerpo1, cuerpoACuerpo2));
        }


        Mazo mazo = new Mazo(unidades, especiales);

        Jugador jugador1 = new Jugador(tablero1, mazo);

        int cartasEsperadas = 12;

        Modificador espia = new Espia(jugador1);
        Unidad unidadEspia = new Unidad(cuerpoACuerpo2, 8, espia);

        jugador1.tomarCartasMazo(10);

        // Act
        unidadEspia.usar();
        Puntaje puntajeCuerpoACuerpo2 = cuerpoACuerpo2.puntajeTotal();

        // Assert
        Assertions.assertTrue(cartasEsperadas == jugador1.verMano().size() && puntajeCuerpoACuerpo2.equals(8));

    }

    @Test
    public void test04unaCartaAgilSePuedeColocarEnLaSeccionCorrespondiente() {
        // Arrange
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();

        Tablero tablero = new Tablero(cuerpoACuerpo, rango, asedio);

        List<Seccion> seccionesPermitidas = new ArrayList<>();
        seccionesPermitidas.add(cuerpoACuerpo);
        seccionesPermitidas.add(rango);

        Modificador agil = new Agil(seccionesPermitidas);

        Unidad hechicero = new Unidad(cuerpoACuerpo, 5, agil);

        // Act
        hechicero.ubicar(rango);
        Puntaje puntajeRango = rango.puntajeTotal();

        // Assert
        Assertions.assertTrue(puntajeRango.equals(5));

    }
}