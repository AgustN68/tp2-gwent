package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima.Clima;
import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.Modificador;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JugadorTest {
    @Test
    public void test01UnJugadorPuedeSeleccionarUnaUnidad() {
        //"Arrange"
        Seccion seccionCuerpoACuerpo = new CuerpoACuerpo();
        Seccion secconRango = new Rango();
        Seccion seccionAsedio = new Asedio();
        Tablero tablero = new Tablero(seccionCuerpoACuerpo, secconRango, seccionAsedio);
        List<Unidad> unidades = new ArrayList();
        List<Especial> especiales = new ArrayList();
        for (int i = 0; i < 15; i++) {
            unidades.add(new Unidad(seccionCuerpoACuerpo,2, new Modificador()));
        }
        for (int i = 0; i < 6; i++) {
            especiales.add(new Clima(seccionCuerpoACuerpo, seccionAsedio));
        }

        Mazo mazo = new Mazo(unidades, especiales);
        Jugador jugador = new Jugador(tablero, mazo);
        jugador.tomarCartasMazo(1);

        //"Act"
        Carta cartaSeleccionada = jugador.seleccionarCarta(0);

        //"Assert"
        assertEquals(Unidad.class, cartaSeleccionada.getClass());
    }
}
