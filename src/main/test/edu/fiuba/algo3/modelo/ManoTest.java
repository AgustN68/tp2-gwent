package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.colocadores.ColocadorAsedio;
import edu.fiuba.algo3.modelo.colocadores.ColocadorCuerpoACuerpo;
import edu.fiuba.algo3.modelo.colocadores.ColocadorRango;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasSuficientesException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManoTest {

    @Test
    public void test01TieneCartasSuficientesLanzaExcepcionCuandoNoAlcanza() {
        Modificador modificador = new Modificador();
        List<Carta> cartas = List.of(new Unidad(new ColocadorRango(), 7, modificador));
        Mano mano = new Mano(new ArrayList<>(cartas));

        assertThrows(NoHayCartasSuficientesException.class, () -> mano.tieneCartasSuficientes(2));
    }

    @Test
    public void test02TomarCartaAgregaUnaCartaALaMano() {
        Mano mano = new Mano(new ArrayList<>());
        Modificador modificador = new Modificador();
        Mazo mazo = new Mazo(List.of(new Unidad(new ColocadorCuerpoACuerpo(), 7, modificador)),new ArrayList<>());

        mano.tomarCarta(mazo);

        assertDoesNotThrow(() -> mano.tieneCartasSuficientes(1));
    }

    @Test
    public void test03AgarrarCartaDevuelveYRemueveLaCartaEnEsaPosicion() {
        Modificador modificador = new Modificador();
        ColocadorAsedio asedio = new ColocadorAsedio();

        List<Carta> cartas = new ArrayList<>();
        cartas.add(new Unidad(asedio, 7, modificador));
        cartas.add(new Unidad(asedio, 3, modificador));

        Mano mano = new Mano(cartas);

        Carta obtenida = mano.agarrarCarta(0);

        assertEquals(1, cartas.size());
    }
}
