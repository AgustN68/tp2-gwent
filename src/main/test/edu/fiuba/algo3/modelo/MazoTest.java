package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Especial.Clima.Clima;
import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Modificador.Modificador;
import edu.fiuba.algo3.modelo.Modificador.SinModificador;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasEspecialesSuficientesException;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasUnidadSuficientesException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MazoTest {
    @Test
    public void test1UnMazoValidarCartasSuficientesParaEmpezarLaPartida(){

        // Arrange
        CuerpoACuerpo cuerpoACuerpo = new CuerpoACuerpo();
        List<Unidad> cartasUnidades = new ArrayList();
        List<Especial> cartasEspeciales = new ArrayList();
        for (int i = 0; i < 15; i++) {
            cartasUnidades.add(new Unidad(cuerpoACuerpo,2, new SinModificador()));
        }
        for (int i = 0; i < 6; i++) {
            cartasEspeciales.add(new Clima(cuerpoACuerpo, cuerpoACuerpo));
        }

        //Act y Assert
        Mazo mazo = new Mazo(cartasUnidades, cartasEspeciales);

    }
    @Test
    public void test2UnMazoTieneCartasDeSobraParaEmpezarLaPartida(){

        // Arrange
        CuerpoACuerpo cuerpoACuerpo = new CuerpoACuerpo();
        List<Unidad> cartasUnidades = new ArrayList();
        List<Especial> cartasEspeciales = new ArrayList();
        for (int i = 0; i < 20; i++) {
            cartasUnidades.add(new Unidad(cuerpoACuerpo,2, new SinModificador()));
        }
        for (int i = 0; i < 9; i++) {
            cartasEspeciales.add(new Clima(cuerpoACuerpo, cuerpoACuerpo));
        }

        //Act y Assert
        Mazo mazo = new Mazo(cartasUnidades, cartasEspeciales);

    }

    @Test
    public void test3UnMazoNoTieneCartasDeUnidadSuficientesParaEmpezarLaPartida(){

        // Arrange
        List<Unidad> cartasUnidades = new ArrayList();
        List<Especial> cartasEspeciales = new ArrayList();
        for (int i = 0; i < 14; i++) {
            cartasUnidades.add(new Unidad(new CuerpoACuerpo(),2, new SinModificador()));
        }
        for (int i = 0; i < 6; i++) {
            cartasEspeciales.add(new Clima(new CuerpoACuerpo(),new CuerpoACuerpo()));
        }


        //Act y Assert
        assertThrows(
                NoHayCartasUnidadSuficientesException.class,
                ()-> new Mazo(cartasUnidades, cartasEspeciales)
        );
    }

    @Test
    public void test4UnMazoNoTieneCartasEspecialesSuficientesParaEmpezarLaPartida(){

        // Arrange
        List<Unidad> cartasUnidades = new ArrayList();
        List<Especial> cartasEspeciales = new ArrayList();
        for (int i = 0; i < 17; i++) {
            cartasUnidades.add(new Unidad(new CuerpoACuerpo(),2, new SinModificador()));
        }
        for (int i = 0; i < 3; i++) {
            cartasEspeciales.add(new Clima(new CuerpoACuerpo(),new CuerpoACuerpo()));
        }


        //Act y Assert
        assertThrows(
                NoHayCartasEspecialesSuficientesException.class,
                ()-> new Mazo(cartasUnidades, cartasEspeciales)
        );
    }
}
