package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Lector.Lector;
import edu.fiuba.algo3.modelo.Lector.LectorJson;
import edu.fiuba.algo3.modelo.Mazo;
import edu.fiuba.algo3.modelo.PilaDeDescarte;
import edu.fiuba.algo3.modelo.Seccion.Asedio;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;
import edu.fiuba.algo3.modelo.Seccion.Rango;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Tablero;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class testEntrega3 {

    private static final String RUTA_JSON = "src/main/java/edu/fiuba/algo3/archivos/gwent.json";

    private static final int POS_MAZO_J1 = 0;
    private static final int POS_MAZO_J2 = 1;

    @Test
    public void test01seExtraenCorrectamenteLosMazosDelJsonyEsValido() {

        // Arrange
        Lector lector = new LectorJson();

        List<Seccion> seccionesJ1 = new ArrayList<>();
        seccionesJ1.add(new CuerpoACuerpo());
        seccionesJ1.add(new Rango());
        seccionesJ1.add(new Asedio());

        List<Seccion> seccionesJ2 = new ArrayList<>();
        seccionesJ2.add(new CuerpoACuerpo());
        seccionesJ2.add(new Rango());
        seccionesJ2.add(new Asedio());

        Tablero tableroJ1 = new Tablero(seccionesJ1);
        Tablero tableroJ2 = new Tablero(seccionesJ2);

        Jugador jugador1 = new Jugador(tableroJ1, new PilaDeDescarte());
        Jugador jugador2 = new Jugador(tableroJ2, new PilaDeDescarte());

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> {
            List<Mazo> mazosLeido = lector.leerMazos(
                    RUTA_JSON,
                    jugador1,
                    jugador2);

            jugador1.setMazo(mazosLeido.get(POS_MAZO_J1));
            jugador2.setMazo(mazosLeido.get(POS_MAZO_J2));
        });


    }
}
