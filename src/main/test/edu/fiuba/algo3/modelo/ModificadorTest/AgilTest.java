package edu.fiuba.algo3.modelo.ModificadorTest;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.Agil;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Tablero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AgilTest {

    @Test
    public void test01unaCartaAgilSePuedeColocarEnLaSeccionOriginal() {
        // Arrange
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();

        List<Seccion> seccionesPermitidas = new ArrayList<>();
        seccionesPermitidas.add(cuerpoACuerpo);
        seccionesPermitidas.add(rango);

        Agil agil = new Agil(seccionesPermitidas);
        Unidad hechicero = new Unidad(cuerpoACuerpo, 5, agil);

        // Act & Assert
        agil.setCarta(hechicero);
        Assertions.assertDoesNotThrow(() -> {
            hechicero.ubicar(cuerpoACuerpo);
        });
    }

    @Test
    public void test02unaCartaAgilSePuedeColocarEnAlgunaDeTresLasSeccionesDisponibles() {
        // Arrange
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();

        List<Seccion> seccionesPermitidas = new ArrayList<>();
        seccionesPermitidas.add(cuerpoACuerpo);
        seccionesPermitidas.add(rango);
        seccionesPermitidas.add(asedio);

        Agil agil = new Agil(seccionesPermitidas);
        Unidad unidad = new Unidad(cuerpoACuerpo, 5, agil);

        // Act & Assert
        agil.setCarta(unidad);
        Assertions.assertDoesNotThrow(() -> {
            unidad.ubicar(asedio);
        });
    }
}
