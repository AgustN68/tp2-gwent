package edu.fiuba.algo3.modelo.Carta.Especial.Clima;

import edu.fiuba.algo3.modelo.Ronda;
import edu.fiuba.algo3.modelo.Seccion.CuerpoACuerpo;

public class Nieve extends Clima {

    public void aplicarClima(Ronda ronda){
        //afectarCuerpoACuerpo();
    }

    public void afectarCuerpoACuerpo(CuerpoACuerpo cuerpoACuerpo){
        cuerpoACuerpo.reducirPuntaje(VALOR);
    }
}
