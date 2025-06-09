package edu.fiuba.algo3.modelo.CartaTest.EspecialTest;

import edu.fiuba.algo3.modelo.Carta.Especial.MoraleBoost;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoraleBoostTest {
    @Test
    public void test01SeAplicaMoraleBoostAUnaSeccion() {
        MoraleBoost moraleBoost = new MoraleBoost();

        CuerpoACuerpo cuerpoACuerpo = new CuerpoACuerpo();

        Unidad soldado = new Unidad(cuerpoACuerpo, 10, new SinModificador());

        soldado.ubicar(cuerpoACuerpo);

        moraleBoost.usar(cuerpoACuerpo);

        Puntaje puntajeObtenido = cuerpoACuerpo.puntajeTotal();

        Assertions.assertTrue(puntajeObtenido.equals(20));
    }

    @Test
    public void test02SeAplicaMoraleBoostAOtraSeccion() {
        MoraleBoost moraleBoost = new MoraleBoost();

        Asedio asedio = new Asedio();

        Unidad catapulta = new Unidad(asedio, 20, new SinModificador());

        catapulta.ubicar(asedio);

        moraleBoost.usar(asedio);

        Puntaje puntajeObtenido = asedio.puntajeTotal();

        Assertions.assertTrue(puntajeObtenido.equals(40));
    }
}
