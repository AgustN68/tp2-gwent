package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.colocadores.ColocadorCuerpoACuerpo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JugadorTest {
    @Test
    public void test01UnJugadorPuedeSeleccionarUnaUnidad() {
        //"Arrange"
        Seccion seccionCuerpoACuerpo = new Seccion();
        Seccion secconRango = new Seccion();
        Seccion seccionAsedio = new Seccion();
        Tablero tablero = new Tablero(seccionCuerpoACuerpo, secconRango, seccionAsedio);
        List<Unidad> unidades = new ArrayList();
        List<Especial> especiales = new ArrayList();
        for (int i = 0; i < 15; i++) {
            unidades.add(new Unidad(new ColocadorCuerpoACuerpo(),2, new Modificador()));
        }
        for (int i = 0; i < 6; i++) {
            especiales.add(new Especial());
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
