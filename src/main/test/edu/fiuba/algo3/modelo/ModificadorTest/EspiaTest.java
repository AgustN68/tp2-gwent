package edu.fiuba.algo3.modelo.ModificadorTest;

import edu.fiuba.algo3.modelo.Carta.Especial.Clima;
import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mazo;
import edu.fiuba.algo3.modelo.Modificador.Espia;
import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Tablero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EspiaTest {

    @Test
    public void test01SeUsaUnEspiaYPermiteAgarrarDosCartas() {
        // Arrange

        CuerpoACuerpo cuerpoACuerpo1 = new CuerpoACuerpo();
        Rango rango1 = new Rango();
        Asedio asedio1 = new Asedio();

        CuerpoACuerpo cuerpoACuerpo2 = new CuerpoACuerpo();

        Tablero tablero1 = new Tablero(cuerpoACuerpo1, rango1, asedio1);

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

        Espia espia = new Espia(jugador1);
        Unidad unidadEspia = new Unidad(cuerpoACuerpo2, 8, espia);

        jugador1.tomarCartasMazo(10);

        // Act
        unidadEspia.usar();

        // Assert
        Assertions.assertEquals(cartasEsperadas, jugador1.verMano().size());
    }

    @Test
    public void test02SeUsaUnEspiaYSoloAgarra1CartaYaQueNoHayLasSuficientesEnElMazo() {

        // Arrange
        CuerpoACuerpo cuerpoACuerpo1 = new CuerpoACuerpo();
        Rango rango1 = new Rango();
        Asedio asedio1 = new Asedio();

        CuerpoACuerpo cuerpoACuerpo2 = new CuerpoACuerpo();

        Tablero tablero1 = new Tablero(cuerpoACuerpo1, rango1, asedio1);

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

        int cartasEsperadas = 22;
        Espia espia = new Espia(jugador1);
        Unidad unidadEspia = new Unidad(cuerpoACuerpo2, 8, espia);
        jugador1.tomarCartasMazo(21);

        // Act
        unidadEspia.usar();

        // Assert
        Assertions.assertEquals(cartasEsperadas, jugador1.verMano().size());
    }
}
