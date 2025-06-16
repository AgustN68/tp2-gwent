package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Especial.Clima;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableroTest {
    @Test
    public void test01UnTableroPuedeCalcularSuPuntajeTotal(){
        //"Arrange"
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);

        Tablero tablero = new Tablero(secciones);

        new Unidad("Nombre", cuerpoACuerpo, 4, new SinModificador()).ubicar(cuerpoACuerpo);
        new Unidad("Nombre", cuerpoACuerpo, 2, new SinModificador()).ubicar(cuerpoACuerpo);
        //"Act"
        Puntaje puntajeObtenido = tablero.calcularPuntaje();

        //"Assert"
        assertEquals(6, puntajeObtenido.obtenerValor());
    }

    @Test
    public void test02UnTableroPuedeCalcularSuPuntajeTotal(){
        //"Arrange"
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);

        Tablero tablero = new Tablero(secciones);

        new Unidad("Nombre", cuerpoACuerpo, 3, new SinModificador()).ubicar(cuerpoACuerpo);
        new Unidad("Nombre", cuerpoACuerpo, 9, new SinModificador()).ubicar(cuerpoACuerpo);
        //"Act"
        Puntaje puntajeObtenido = tablero.calcularPuntaje();

        //"Assert"
        assertEquals(12, puntajeObtenido.obtenerValor());
    }

    @Test
    public void test03UnTableroPuedeLimpiarElClima(){
        //"Arrange"
        Seccion cuerpoACuerpo1 = new CuerpoACuerpo();
        List<Seccion> seccionesTablero1 = new ArrayList<>();
        seccionesTablero1.add(cuerpoACuerpo1);
        Tablero tablero1 = new Tablero(seccionesTablero1);

        Seccion cuerpoACuerpo2 = new CuerpoACuerpo();
        List<Seccion> seccionesTablero2 = new ArrayList<>();
        seccionesTablero2.add(cuerpoACuerpo2);
        Tablero tablero2 = new Tablero(seccionesTablero2);

        Clima clima = new Clima(cuerpoACuerpo1, cuerpoACuerpo2);

        new Unidad("Nombre", cuerpoACuerpo1, 4, new SinModificador()).ubicar(cuerpoACuerpo1);
        new Unidad("Nombre", cuerpoACuerpo1, 2, new SinModificador()).ubicar(cuerpoACuerpo1);

        clima.usar();

        //"Act"
        tablero1.limpiarClima();

        //"Assert"
        assertEquals(6, tablero1.calcularPuntaje().obtenerValor());
    }

    @Test
    public void test04UnTableroPuedeRemoverLasUnidadesMasFuertes(){
        //"Arrange"
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();
        List<Seccion> seccionesTablero = new ArrayList<>();
        seccionesTablero.add(cuerpoACuerpo);
        seccionesTablero.add(rango);
        seccionesTablero.add(asedio);
        Tablero tablero = new Tablero(seccionesTablero);

        new Unidad("Nombre", cuerpoACuerpo, 8, new SinModificador()).ubicar(cuerpoACuerpo);
        new Unidad("Nombre", asedio, 4, new SinModificador()).ubicar(asedio);
        new Unidad("Nombre", rango, 3, new SinModificador()).ubicar(rango);

        //"Act"
        tablero.removerUnidadesMasFuertes();

        //"Assert"
        assertEquals(7, tablero.calcularPuntaje().obtenerValor());
    }

    @Test
    public void test05UnTableroPuedeRemoverLasUnidadesMasFuertes(){
        //"Arrange"
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();
        List<Seccion> seccionesTablero = new ArrayList<>();
        seccionesTablero.add(cuerpoACuerpo);
        seccionesTablero.add(rango);
        seccionesTablero.add(asedio);
        Tablero tablero = new Tablero(seccionesTablero);

        new Unidad("Nombre", cuerpoACuerpo, 4, new SinModificador()).ubicar(cuerpoACuerpo);
        new Unidad("Nombre", asedio, 8, new SinModificador()).ubicar(asedio);
        new Unidad("Nombre", rango, 3, new SinModificador()).ubicar(rango);

        //"Act"
        tablero.removerUnidadesMasFuertes();

        //"Assert"
        assertEquals(7, tablero.calcularPuntaje().obtenerValor());
    }

    @Test
    public void test06UnTableroPuedeRemoverLasUnidadesMasFuertes(){
        //"Arrange"
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();
        List<Seccion> seccionesTablero = new ArrayList<>();
        seccionesTablero.add(cuerpoACuerpo);
        seccionesTablero.add(rango);
        seccionesTablero.add(asedio);
        Tablero tablero = new Tablero(seccionesTablero);

        new Unidad("Nombre", cuerpoACuerpo, 4, new SinModificador()).ubicar(cuerpoACuerpo);
        new Unidad("Nombre", asedio, 8, new SinModificador()).ubicar(asedio);
        new Unidad("Nombre", rango, 9, new SinModificador()).ubicar(rango);

        //"Act"
        tablero.removerUnidadesMasFuertes();

        //"Assert"
        assertEquals(12, tablero.calcularPuntaje().obtenerValor());
    }

    @Test
    public void test07UnTableroPuedeRemoverLasUnidadesMasFuertes(){
        //"Arrange"
        Seccion cuerpoACuerpo = new CuerpoACuerpo();
        Seccion rango = new Rango();
        Seccion asedio = new Asedio();
        List<Seccion> seccionesTablero = new ArrayList<>();
        seccionesTablero.add(cuerpoACuerpo);
        seccionesTablero.add(rango);
        seccionesTablero.add(asedio);
        Tablero tablero = new Tablero(seccionesTablero);

        new Unidad("Nombre", cuerpoACuerpo, 4, new SinModificador()).ubicar(cuerpoACuerpo);
        new Unidad("Nombre", asedio, 9, new SinModificador()).ubicar(asedio);
        new Unidad("Nombre", rango, 9, new SinModificador()).ubicar(rango);

        //"Act"
        tablero.removerUnidadesMasFuertes();

        //"Assert"
        assertEquals(4, tablero.calcularPuntaje().obtenerValor());
    }
}
