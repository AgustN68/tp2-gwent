package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
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
        List<Unidad> cartasUnidades = new ArrayList();
        List<Especial> cartasEspeciales = new ArrayList();
        for (int i = 0; i < 15; i++) {
            cartasUnidades.add(new Unidad(new ColocadorCuerpoACuerpo(),2, new Modificador()));
        }
        for (int i = 0; i < 6; i++) {
            cartasEspeciales.add(new Especial());
        }

        Mazo mazo = new Mazo(cartasUnidades, cartasEspeciales);

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
