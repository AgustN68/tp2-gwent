package edu.fiuba.algo3.modelo.CartaTest;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EspecialTest {


    @Deprecated
    @Test
    public void test01SeAplicaElEfectoNieveALaPartida(){
        Seccion cuerpoACuerpo1 = new Seccion();
        Seccion cuerpoACuerpo2 = new Seccion();
        Unidad unidad1 = new Unidad(cuerpoACuerpo1,5,new Modificador());
        Unidad unidad2 = new Unidad(cuerpoACuerpo2,6,new Modificador());
        Nieve nieve = new Nieve(cuerpoACuerpo1, cuerpoACuerpo2);

        nieve.usar();
        //Puntaje puntajeSeccion1 = cuerpoACuerpo1.calcularPuntaje();
        //Puntaje puntajeSeccion2 = cuerpoACuerpo2.calcularPuntaje();

        //Assertions.assertTrue(puntajeSeccion1.equals(1) && puntajeSeccion2.equals(1));

    }
}
