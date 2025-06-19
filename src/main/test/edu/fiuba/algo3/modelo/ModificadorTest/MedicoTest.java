package edu.fiuba.algo3.modelo.ModificadorTest;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima;
import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.Medico;
import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MedicoTest {

    @Test
    public void test01SeUsaUnaCartaMedicoYSeUtilizaLaCartaDeLaPilaDeDescarte() {

        // Arrange
        CuerpoACuerpo cuerpoACuerpo = new CuerpoACuerpo();
        Rango rango = new Rango();
        Asedio asedio = new Asedio();

        List<Seccion> secciones = new ArrayList<>();
        secciones.add(cuerpoACuerpo);
        secciones.add(rango);
        secciones.add(asedio);
        Tablero tablero1 = new Tablero(secciones);

        List<Unidad> unidades = new ArrayList<>();
        List<Especial> especiales = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            unidades.add(new Unidad("Nombre", cuerpoACuerpo, 2, new SinModificador()));
        }

        for (int i = 0; i < 6; i++) {
            List<Seccion> seccionesAfectadas = new ArrayList<>();
            seccionesAfectadas.add(cuerpoACuerpo);
            seccionesAfectadas.add(new CuerpoACuerpo());

            especiales.add(new Clima(seccionesAfectadas));
        }
        Mazo mazo = new Mazo(unidades, especiales);
        PilaDeDescarte pila = new PilaDeDescarte();
        pila.descartarUna(new Unidad("Nombre", cuerpoACuerpo, 5, new SinModificador()));

        Jugador jugador1 = new Jugador(tablero1, mazo, pila);

        Medico medico = new Medico(jugador1);
        Unidad unidadMedico = new Unidad("Nombre", cuerpoACuerpo, 8, medico);

        // Act
        unidadMedico.usar();
        Puntaje puntajeTotal = cuerpoACuerpo.puntajeTotal();

        // Assert
        Assertions.assertTrue(puntajeTotal.equals(13));
    }

}
