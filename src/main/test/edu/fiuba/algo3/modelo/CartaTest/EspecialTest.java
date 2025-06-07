package edu.fiuba.algo3.modelo.CartaTest;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima.Clima;
import edu.fiuba.algo3.modelo.Carta.Especial.TierraArrasada;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class EspecialTest {

    @Test
    public void test01SeAplicaElEfectoNieveALaPartida(){
        Seccion cuerpoACuerpo1 = new CuerpoACuerpo();
        Seccion cuerpoACuerpo2 = new CuerpoACuerpo();
        Unidad unidad1 = new Unidad(cuerpoACuerpo1,5,new SinModificador());
        Unidad unidad2 = new Unidad(cuerpoACuerpo2,6,new SinModificador());
        Clima nieve = new Clima(cuerpoACuerpo1, cuerpoACuerpo2);

        cuerpoACuerpo1.ubicar(unidad1);
        cuerpoACuerpo2.ubicar(unidad2);

        nieve.usar();
        Puntaje puntajeSeccion1 = cuerpoACuerpo1.puntajeTotal();
        Puntaje puntajeSeccion2 = cuerpoACuerpo2.puntajeTotal();

        Assertions.assertTrue(puntajeSeccion1.equals(1) && puntajeSeccion2.equals(1));

    }
    @Test
    public void test02SeUsaTierraArrasadaYSeEliminanLasCartasCorrectamente() {
        // Arrange
        CuerpoACuerpo cuerpoACuerpo1 = new CuerpoACuerpo();
        Rango rango1 = new Rango();
        Asedio asedio1 = new Asedio();
        CuerpoACuerpo cuerpoACuerpo2 = new CuerpoACuerpo();
        Rango rango2 = new Rango();
        Asedio asedio2 = new Asedio();

        Tablero tablero1 = new Tablero(cuerpoACuerpo1, rango1, asedio1);
        Tablero tablero2 = new Tablero(cuerpoACuerpo2, rango2, asedio2);


        Unidad unidad1 = new Unidad(cuerpoACuerpo1, 8, new SinModificador());
        Unidad unidad2 = new Unidad(cuerpoACuerpo2, 9, new SinModificador());
        Unidad unidad3 = new Unidad(rango2, 10, new SinModificador());

        unidad1.ubicar(cuerpoACuerpo1);
        unidad2.ubicar(cuerpoACuerpo2);
        unidad3.ubicar(rango2);

        // Act
        TierraArrasada tierraArrasada = new TierraArrasada(tablero2);

        tierraArrasada.usar();

        Puntaje puntajeObtenido = tablero2.calcularPuntaje();

        // Assert
        Assertions.assertTrue(puntajeObtenido.equals(9));
    }
}
