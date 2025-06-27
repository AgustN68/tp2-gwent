package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima.Clima;
import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.Modificador;
import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasSuficientesException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManoTest {

    @Test
    public void test01TieneCartasSuficientesLanzaExcepcionCuandoNoAlcanza() {
        Modificador modificador = new SinModificador();
        List<Carta> cartas = List.of(new Unidad("Nombre", new Rango(), 7, modificador));
        Mano mano = new Mano(new ArrayList<>(cartas));

        assertThrows(NoHayCartasSuficientesException.class, () -> mano.tieneCartasSuficientes(2));
    }

    @Test
    public void test02TomarCartaAgregaUnaCartaALaMano() {
        Mano mano = new Mano(new ArrayList<>());
        Modificador modificador = new SinModificador();
        List<Unidad> cartasUnidades = new ArrayList();
        List<Especial> cartasEspeciales = new ArrayList();
        for (int i = 0; i < 15; i++) {
            cartasUnidades.add(new Unidad("Nombre", new CuerpoACuerpo(),2, new SinModificador()));
        }
        for (int i = 0; i < 6; i++) {
            List<Seccion> seccionesAfectadas = new ArrayList<>();
            seccionesAfectadas.add(new CuerpoACuerpo());
            seccionesAfectadas.add(new CuerpoACuerpo());

            cartasEspeciales.add(new Clima(seccionesAfectadas));
        }

        Mazo mazo = new Mazo(cartasUnidades, cartasEspeciales);

        mano.tomarCarta(mazo);

        assertDoesNotThrow(() -> mano.tieneCartasSuficientes(1));
    }

    @Test
    public void test03AgarrarCartaDevuelveYRemueveLaCartaEnEsaPosicion() {
        Modificador modificador = new SinModificador();
        Asedio asedio = new Asedio();

        List<Carta> cartas = new ArrayList<>();
        cartas.add(new Unidad("Nombre", asedio, 7, modificador));
        cartas.add(new Unidad("Nombre", asedio, 3, modificador));

        Mano mano = new Mano(cartas);

        Carta obtenida = mano.agarrarCarta(0);

        assertEquals(1, cartas.size());
    }

    @Test
    public void test04AgarrarCartaPasandoleUnaPocisionNegativaYDebeDarError() {
        Modificador modificador = new SinModificador();
        Asedio asedio = new Asedio();

        List<Carta> cartas = new ArrayList<>();
        cartas.add(new Unidad("Nombre", asedio, 7, modificador));
        cartas.add(new Unidad("Nombre", asedio, 3, modificador));

        Mano mano = new Mano(cartas);

        assertThrows(IndexOutOfBoundsException.class, () -> {mano.agarrarCarta(-1);
        });
    }

    @Test
    public void test05AgarrarCartaPasandoleUnaPocisionMayorALaPermitidaYDebeDarError() {
        Modificador modificador = new SinModificador();
        Asedio asedio = new Asedio();

        List<Carta> cartas = new ArrayList<>();
        cartas.add(new Unidad("Nombre", asedio, 7, modificador));
        cartas.add(new Unidad("Nombre", asedio, 3, modificador));

        Mano mano = new Mano(cartas);

        assertThrows(IndexOutOfBoundsException.class, () -> {mano.agarrarCarta(5);
        });
    }

}
