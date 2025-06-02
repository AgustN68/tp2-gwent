package edu.fiuba.algo3.modelo.colocadores;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Tablero;

public class ColocadorCuerpoACuerpo implements Colocador {

    @Override
    public void colocar(Unidad unidad, Tablero tablero) {
        tablero.ubicarCuerpoACuerpo(unidad);
    }
}
