package edu.fiuba.algo3.modelo.ModificadorTest;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.Legendaria;
import edu.fiuba.algo3.modelo.Modificador.MoraleBoost;
import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoraleBoostTest {

    @Test
    public void test01unaUnidadConMoraleBoostEfectivamenteIncrementaPuntaje() {

        // Arrange

        CuerpoACuerpo cuerpoACuerpo = new CuerpoACuerpo();

        Unidad unidad = new Unidad("Nombre", cuerpoACuerpo, 5, new SinModificador());
        Unidad unidad2 = new Unidad("Nombre", cuerpoACuerpo, 7, new SinModificador());
        Unidad unidad3 = new Unidad("Nombre", cuerpoACuerpo, 3, new SinModificador());

        MoraleBoost mb = new MoraleBoost();
        Unidad unidadMB = new Unidad("Nombre", cuerpoACuerpo, 5, mb);
        mb.setCarta(unidadMB);

        // Act
        unidad.usar();
        unidad2.usar();
        unidad3.usar();
        unidadMB.usar();

        Puntaje puntajeSeccion = cuerpoACuerpo.puntajeTotal();

        // Assert
        Assertions.assertEquals(24, puntajeSeccion.obtenerValor());
    }

    @Test
    public void test02unaUnidadConMoraleBoostNoPotenciaAUnaLegendaria() {

        // Arrange
        CuerpoACuerpo cuerpoACuerpo = new CuerpoACuerpo();

        Unidad unidad = new Unidad("Nombre", cuerpoACuerpo, 5, new Legendaria());

        MoraleBoost mb = new MoraleBoost();
        Unidad unidadMB = new Unidad("Nombre", cuerpoACuerpo, 5, mb);
        mb.setCarta(unidadMB);

        // Act
        unidad.usar();
        unidadMB.usar();

        Puntaje puntajeSeccion = cuerpoACuerpo.puntajeTotal();

        // Assert
        Assertions.assertEquals(11, puntajeSeccion.obtenerValor());
    }
}
