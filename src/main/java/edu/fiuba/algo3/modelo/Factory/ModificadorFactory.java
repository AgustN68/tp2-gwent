package edu.fiuba.algo3.modelo.Factory;

import edu.fiuba.algo3.modelo.Modificador.MoraleBoost;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Modificador.*;
import edu.fiuba.algo3.modelo.exceptions.ModificadorInexistenteException;

public class ModificadorFactory {

    public static Modificador crearModificador(String modificador, Jugador jugador, Unidad unidad) {
        switch (modificador) {

            case "null":
                return new SinModificador();

            case "Legendaria":
                return new Legendaria();

            case "Medico":
                return new Medico(jugador);

            case "Carta Unida":
                return new Unida();

            case "Morale Boost":
                return new MoraleBoost();

            case "Espia":
                return new Espia(jugador);

            default:
                throw new ModificadorInexistenteException("El modificador ingresado no existe: " + modificador);
        }
    }
}

