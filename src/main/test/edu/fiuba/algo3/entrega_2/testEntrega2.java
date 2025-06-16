package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima;
import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Carta.Especial.Despejado;
import edu.fiuba.algo3.modelo.Carta.Especial.TierraArrasada;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.Agil;
import edu.fiuba.algo3.modelo.Modificador.Espia;
import edu.fiuba.algo3.modelo.Modificador.Medico;
import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.exceptions.UnidadNoSePuedeUbicarEnEstaSeccionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class testEntrega2 {
    @Test
    public void test01SePuedeEliminarEfectoClima(){
        Seccion cuerpoACuerpo1 = new CuerpoACuerpo();
        Seccion cuerpoACuerpo2 = new CuerpoACuerpo();

        List<Seccion> secciones1 = new ArrayList<>();
        secciones1.add(cuerpoACuerpo1);
        secciones1.add(new Rango());
        secciones1.add(new Asedio());

        List<Seccion> secciones2 = new ArrayList<>();
        secciones2.add(cuerpoACuerpo2);
        secciones2.add(new Rango());
        secciones2.add(new Asedio());

        Tablero tablero1 = new Tablero(secciones1);
        Tablero tablero2 = new Tablero(secciones2);
        int puntosCarta1 = 5;
        int puntosCarta2 = 6;
        Unidad unidad1 = new Unidad("Nombre", cuerpoACuerpo1,puntosCarta1,new SinModificador());
        Unidad unidad2 = new Unidad("Nombre", cuerpoACuerpo2,puntosCarta2,new SinModificador());
        Clima nieve = new Clima(cuerpoACuerpo1, cuerpoACuerpo2);
        Despejado despejadoClima = new Despejado(tablero1, tablero2);

        cuerpoACuerpo1.ubicar(unidad1);
        cuerpoACuerpo2.ubicar(unidad2);

        nieve.usar();

        despejadoClima.usar();

        Puntaje puntajeSeccion1 = cuerpoACuerpo1.puntajeTotal();
        Puntaje puntajeSeccion2 = cuerpoACuerpo2.puntajeTotal();

        Assertions.assertTrue(puntajeSeccion1.equals(puntosCarta1) && puntajeSeccion2.equals(puntosCarta2));

    }
    @Test
    public void test02SeUsaTierraArrasadaYSeEliminanLasCartasCorrectamente() {
        // Arrange
        CuerpoACuerpo cuerpoACuerpo = new CuerpoACuerpo();
        Rango rango = new Rango();
        Asedio asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);

        Tablero tablero = new Tablero(secciones);

        Unidad unidad1 = new Unidad("Nombre", cuerpoACuerpo, 8, new SinModificador());
        Unidad unidad2 = new Unidad("Nombre", cuerpoACuerpo, 9, new SinModificador());
        Unidad unidad3 = new Unidad("Nombre", rango, 10, new SinModificador());
        Unidad unidad4 = new Unidad("Nombre", asedio, 14, new SinModificador());

        unidad1.ubicar(cuerpoACuerpo);
        unidad2.ubicar(cuerpoACuerpo);
        unidad3.ubicar(rango);
        unidad4.ubicar(asedio);

        // Act
        TierraArrasada tierraArrasada = new TierraArrasada();

        tierraArrasada.usar(tablero);

        Puntaje puntajeObtenido = tablero.calcularPuntaje();

        // Assert
        assertEquals(8+9+10, puntajeObtenido.obtenerValor());
    }

    @Test
    public void test03UnaCartaEspiaSeColocaEnElTablero2YTePermiteAgarrar2Cartas() {
        // Arrange

        CuerpoACuerpo cuerpoACuerpo1 = new CuerpoACuerpo();
        Rango rango1 = new Rango();
        Asedio asedio1 = new Asedio();

        List<Seccion> secciones1 = new ArrayList<>();
        secciones1.add(cuerpoACuerpo1);
        secciones1.add(rango1);
        secciones1.add(asedio1);

        CuerpoACuerpo cuerpoACuerpo2 = new CuerpoACuerpo();
        Rango rango2 = new Rango();
        Asedio asedio2 = new Asedio();

        List<Seccion> secciones2 = new ArrayList<>();
        secciones2.add(cuerpoACuerpo2);
        secciones2.add(rango2);
        secciones2.add(asedio2);

        Tablero tablero1 = new Tablero(secciones1);
        Tablero tablero2 = new Tablero(secciones2);


        List<Unidad> unidades = new ArrayList<>();
        List<Especial> especiales = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            unidades.add(new Unidad("Nombre", cuerpoACuerpo1, 2, new SinModificador()));
        }

        for (int i = 0; i < 6; i++) {
            especiales.add(new Clima(cuerpoACuerpo1, cuerpoACuerpo2));
        }


        Mazo mazo = new Mazo(unidades, especiales);

        Jugador jugador1 = new Jugador(tablero1, mazo, new PilaDeDescarte());

        int cartasEsperadas = 12;

        Espia espia = new Espia(jugador1);
        Unidad unidadEspia = new Unidad("Nombre", cuerpoACuerpo2, 8, espia);

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


        List<Seccion> seccionesPermitidas = new ArrayList<>();
        seccionesPermitidas.add(cuerpoACuerpo);
        seccionesPermitidas.add(rango);

        Agil agil = new Agil(seccionesPermitidas);
        Unidad hechicero = new Unidad("Nombre", cuerpoACuerpo, 5, agil);
        agil.setCarta(hechicero);

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> {
            hechicero.ubicar(rango);
        });

    }


    @Test
    public void test05unaCartaAgilNoSePuedeColocarEnUnaSeccionNoPermitida() {
        // Arrange
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();

        List<Seccion> seccionesPermitidas = new ArrayList<>();
        seccionesPermitidas.add(cuerpoACuerpo);
        seccionesPermitidas.add(rango);

        Agil agil = new Agil(seccionesPermitidas);
        Unidad hechicero = new Unidad("Nombre", cuerpoACuerpo, 5, agil);
        agil.setCarta(hechicero);

        // Act & Assert
        Assertions.assertThrows(UnidadNoSePuedeUbicarEnEstaSeccionException.class, () -> {
            hechicero.ubicar(asedio);
        });
    }

    @Test
    public void test06SeUsaUnaCartaMedicoYSeUtilizaLaCartaDeLaPilaDeDescarte() {

        // Arrange
        CuerpoACuerpo cuerpoACuerpo = new CuerpoACuerpo();
        Rango rango = new Rango();
        Asedio asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);
        Tablero tablero1 = new Tablero(secciones);

        List<Unidad> unidades = new ArrayList<>();
        List<Especial> especiales = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            unidades.add(new Unidad("Nombre", cuerpoACuerpo, 2, new SinModificador()));
        }

        for (int i = 0; i < 6; i++) {
            especiales.add(new Clima(cuerpoACuerpo, new CuerpoACuerpo()));
        }
        Mazo mazo = new Mazo(unidades, especiales);
        PilaDeDescarte pila = new PilaDeDescarte();
        pila.descartarUna(new Unidad("Nombre", cuerpoACuerpo, 5, new SinModificador()));

        Jugador jugador1 = new Jugador(tablero1, mazo, pila);

        Medico medico = new Medico(jugador1);
        Unidad unidadMedico = new Unidad("Nombre", cuerpoACuerpo, 8, medico);

        // Act
        unidadMedico.usar();
        Puntaje puntajeTotal = cuerpoACuerpo.puntajeTotal();

        // Assert
        Assertions.assertTrue(puntajeTotal.equals(13));
    }
}