package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.exceptions.NoHayCartasEspecialesSuficientesException;
import edu.fiuba.algo3.modelo.exceptions.NoHayCartasUnidadSuficientesException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MazoTest {
    @Test
    public void test1UnMazoTieneCartasSuficientesParaEmpezarLaPartida(){

        // Arrange
        List<Unidad> cartasUnidades = new ArrayList();
        List<Especial> cartasEspeciales = new ArrayList();
        for (int i = 0; i < 15; i++) {
            cartasUnidades.add(new Unidad(new Seccion(),2));
        }
        for (int i = 0; i < 6; i++) {
            cartasEspeciales.add(new Especial());
        }

        Mazo mazo = new Mazo(cartasUnidades, cartasEspeciales);

        //Act y Assert
        assert(mazo.tieneCartasSuficientes(15,6));
    }
    @Test
    public void test2UnMazoTieneCartasDeSobraParaEmpezarLaPartida(){

        // Arrange
        List<Unidad> cartasUnidades = new ArrayList();
        List<Especial> cartasEspeciales = new ArrayList();
        for (int i = 0; i < 20; i++) {
            cartasUnidades.add(new Unidad(new Seccion(),2));
        }
        for (int i = 0; i < 9; i++) {
            cartasEspeciales.add(new Especial());
        }

        Mazo mazo = new Mazo(cartasUnidades, cartasEspeciales);

        //Act y Assert
        assert(mazo.tieneCartasSuficientes(15,6));
    }

    @Test
    public void test3UnMazoNoTieneCartasDeUnidadSuficientesParaEmpezarLaPartida(){

        // Arrange
        List<Unidad> cartasUnidades = new ArrayList();
        List<Especial> cartasEspeciales = new ArrayList();
        for (int i = 0; i < 14; i++) {
            cartasUnidades.add(new Unidad(new Seccion(),2));
        }
        for (int i = 0; i < 6; i++) {
            cartasEspeciales.add(new Especial());
        }

        Mazo mazo = new Mazo(cartasUnidades, cartasEspeciales);

        //Act y Assert
        assertThrows(NoHayCartasUnidadSuficientesException.class,
                ()->{
            mazo.tieneCartasSuficientes(15,6);
                });
    }

    @Test
    public void test4UnMazoNoTieneCartasEspecialesSuficientesParaEmpezarLaPartida(){

        // Arrange
        List<Unidad> cartasUnidades = new ArrayList();
        List<Especial> cartasEspeciales = new ArrayList();
        for (int i = 0; i < 17; i++) {
            cartasUnidades.add(new Unidad(new Seccion(),2));
        }
        for (int i = 0; i < 3; i++) {
            cartasEspeciales.add(new Especial());
        }

        Mazo mazo = new Mazo(cartasUnidades, cartasEspeciales);

        //Act y Assert
        assertThrows(NoHayCartasEspecialesSuficientesException.class,
                ()->{
                    mazo.tieneCartasSuficientes(15,6);
                });
    }
}
