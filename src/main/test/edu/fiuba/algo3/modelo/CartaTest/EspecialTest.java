package edu.fiuba.algo3.modelo.CartaTest;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.colocadores.ColocadorCuerpoACuerpo;
import org.junit.jupiter.api.Test;

public class EspecialTest {


    @Deprecated
    @Test
    public void test01SeAplicaElEfectoNieveALaPartida(){
        Seccion cuerpoACuerpo1 = new Seccion();
        Seccion cuerpoACuerpo2 = new Seccion();
        Unidad unidad1 = new Unidad(new ColocadorCuerpoACuerpo(),5,new Modificador());
        Unidad unidad2 = new Unidad(new ColocadorCuerpoACuerpo(),6,new Modificador());
        Clima nieve = new Clima(cuerpoACuerpo1, cuerpoACuerpo2);

        nieve.usar(new Tablero(cuerpoACuerpo1, new Seccion(), new Seccion()));
        //Puntaje puntajeSeccion1 = cuerpoACuerpo1.calcularPuntaje();
        //Puntaje puntajeSeccion2 = cuerpoACuerpo2.calcularPuntaje();

        //Assertions.assertTrue(puntajeSeccion1.equals(1) && puntajeSeccion2.equals(1));

    }
}
