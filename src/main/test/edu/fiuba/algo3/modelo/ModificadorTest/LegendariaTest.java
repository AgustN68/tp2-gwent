package edu.fiuba.algo3.modelo.ModificadorTest;

import edu.fiuba.algo3.modelo.Carta.Especial.MoraleBoost;
import edu.fiuba.algo3.modelo.Carta.Especial.TierraArrasada;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.Legendaria;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Tablero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LegendariaTest {

    @Test
    public void test01TierraArrasadaNoAfectaAUnaCartaLegendaria() {
        // Arrange
        CuerpoACuerpo cuerpoACuerpo = new CuerpoACuerpo();
        Rango rango = new Rango();
        Asedio asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);

        Tablero tablero = new Tablero(secciones);

        Unidad unidad1 = new Unidad("Guerrero", cuerpoACuerpo, 15, new Legendaria());

        unidad1.ubicar(cuerpoACuerpo);

        // Act
        TierraArrasada tierraArrasada = new TierraArrasada();

        tierraArrasada.usar(secciones);

        Puntaje puntajeObtenido = tablero.calcularPuntaje();

        // Assert
        assertEquals(15, puntajeObtenido.obtenerValor());
    }

    @Test
    public void test02MoraleBoostNoAfectaACartaLegendaria() {
        MoraleBoost moraleBoost = new MoraleBoost();

        CuerpoACuerpo cuerpoACuerpo = new CuerpoACuerpo();

        Unidad soldado = new Unidad("Soldado", cuerpoACuerpo, 10, new Legendaria());

        soldado.ubicar(cuerpoACuerpo);

        moraleBoost.usar(cuerpoACuerpo);

        Puntaje puntajeObtenido = cuerpoACuerpo.puntajeTotal();

        Assertions.assertTrue(puntajeObtenido.equals(10));
    }
}
