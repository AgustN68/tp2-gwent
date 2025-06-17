package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.Lector.Lector;
import edu.fiuba.algo3.modelo.Lector.LectorJson;
import org.junit.Test;

public class testEntrega3 {

    @Test
    public void test01seExtraenCorrectamenteLasUnidadesDelJson() {
        Lector lector = new LectorJson();

        lector.leerMazos("src/main/java/edu/fiuba/algo3/archivos/gwent.json");


    }
}
