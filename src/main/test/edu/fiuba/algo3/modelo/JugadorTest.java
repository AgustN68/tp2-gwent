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

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class JugadorTest {
    @Test
    public void test01UnJugadorPuedeSeleccionarUnaUnidad() {
        //"Arrange"
        Seccion seccionCuerpoACuerpo = new CuerpoACuerpo();
        Seccion secconRango = new Rango();
        Seccion seccionAsedio = new Asedio();
        Tablero tablero = new Tablero(seccionCuerpoACuerpo, secconRango, seccionAsedio);

        Mazo mazoUnidades = mock(Mazo.class);
        when(mazoUnidades.obtenerCarta()).thenReturn(new Unidad(new CuerpoACuerpo(), 7, new SinModificador()));

        Jugador jugador = new Jugador(tablero, mazoUnidades);
        jugador.tomarCartasMazo(1);

        //"Act"
        Carta cartaSeleccionada = jugador.seleccionarCarta(0);

        //"Assert"
        assertEquals(Unidad.class, cartaSeleccionada.getClass());
    }
}
