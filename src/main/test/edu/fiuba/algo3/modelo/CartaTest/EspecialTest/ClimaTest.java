package edu.fiuba.algo3.modelo.CartaTest.EspecialTest;

import edu.fiuba.algo3.modelo.Carta.Especial.Clima;
import edu.fiuba.algo3.modelo.Carta.Especial.Despejado;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Tablero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClimaTest {

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
    public void test02SeAplicaElClimaNieblaALaPartida(){
        Seccion rango1 = new Rango();
        Seccion rango2 = new Rango();
        Unidad unidad1 = new Unidad(rango1,5,new SinModificador());
        Unidad unidad2 = new Unidad(rango2,6,new SinModificador());
        Clima niebla = new Clima(rango1, rango2);

        rango1.ubicar(unidad1);
        rango2.ubicar(unidad2);

        niebla.usar();
        Puntaje puntajeSeccion1 = rango1.puntajeTotal();
        Puntaje puntajeSeccion2 = rango2.puntajeTotal();

        Assertions.assertTrue(puntajeSeccion1.equals(1) && puntajeSeccion2.equals(1));
    }

    @Test
    public void test03SeAplicaElClimaLluviaALaPartida(){
        Seccion asedio1 = new Asedio();
        Seccion asedio2 = new Asedio();
        Unidad unidad1 = new Unidad(asedio1,5,new SinModificador());
        Unidad unidad2 = new Unidad(asedio2,6,new SinModificador());
        Clima lluvia = new Clima(asedio1, asedio2);

        asedio1.ubicar(unidad1);
        asedio2.ubicar(unidad2);

        lluvia.usar();
        Puntaje puntajeSeccion1 = asedio1.puntajeTotal();
        Puntaje puntajeSeccion2 = asedio2.puntajeTotal();

        Assertions.assertTrue(puntajeSeccion1.equals(1) && puntajeSeccion2.equals(1));
    }

    @Test
    public void test04SeAplicaElEfectoNieveALaPartidaYCuandoFinalizaSeVuelveAlEstadoOriginal(){

        Seccion cuerpoACuerpo1 = new CuerpoACuerpo();
        Seccion cuerpoACuerpo2 = new CuerpoACuerpo();
        Unidad unidad1 = new Unidad(cuerpoACuerpo1,5,new SinModificador());
        Unidad unidad2 = new Unidad(cuerpoACuerpo2,6,new SinModificador());
        Clima nieve = new Clima(cuerpoACuerpo1, cuerpoACuerpo2);
        Despejado despejado = new Despejado(new Tablero(cuerpoACuerpo1, cuerpoACuerpo1, cuerpoACuerpo1), new Tablero(cuerpoACuerpo2, cuerpoACuerpo2, cuerpoACuerpo2));

        cuerpoACuerpo1.ubicar(unidad1);
        cuerpoACuerpo2.ubicar(unidad2);

        nieve.usar();

        despejado.usar();

        Puntaje puntajeSeccion1 = cuerpoACuerpo1.puntajeTotal();
        Puntaje puntajeSeccion2 = cuerpoACuerpo2.puntajeTotal();

        Assertions.assertTrue(puntajeSeccion1.equals(5) && puntajeSeccion2.equals(6));
    }

    @Test
    public void test05SeEliminaElClimaNiebla(){

        Seccion rango1 = new Rango();
        Seccion rango2 = new Rango();
        Unidad unidad1 = new Unidad(rango1,5,new SinModificador());
        Unidad unidad2 = new Unidad(rango2,6,new SinModificador());
        Clima niebla = new Clima(rango1, rango2);
        Despejado despejado = new Despejado(new Tablero(rango1, rango1, rango1), new Tablero(rango2, rango2, rango2));

        rango1.ubicar(unidad1);
        rango2.ubicar(unidad2);

        niebla.usar();

        despejado.usar();

        Puntaje puntajeSeccion1 = rango1.puntajeTotal();
        Puntaje puntajeSeccion2 = rango2.puntajeTotal();

        Assertions.assertTrue(puntajeSeccion1.equals(5) && puntajeSeccion2.equals(6));
    }

    @Test
    public void test06SeEliminaElClimaLluvia(){

        Seccion asedio1 = new Asedio();
        Seccion asedio2 = new Asedio();
        Unidad unidad1 = new Unidad(asedio1,5,new SinModificador());
        Unidad unidad2 = new Unidad(asedio2,6,new SinModificador());
        Clima niebla = new Clima(asedio1, asedio2);
        Despejado despejado = new Despejado(new Tablero(asedio1, asedio1, asedio1), new Tablero(asedio2, asedio2, asedio2));

        asedio1.ubicar(unidad1);
        asedio2.ubicar(unidad2);

        niebla.usar();

        despejado.usar();

        Puntaje puntajeSeccion1 = asedio1.puntajeTotal();
        Puntaje puntajeSeccion2 = asedio2.puntajeTotal();

        Assertions.assertTrue(puntajeSeccion1.equals(5) && puntajeSeccion2.equals(6));
    }


}
