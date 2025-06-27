package edu.fiuba.algo3.modelo.Factory;

import edu.fiuba.algo3.modelo.Modificador.MoraleBoostMod;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Modificador.*;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.exceptions.ModificadorInexistenteException;
import java.util.List;

public class ModificadorFactory {

    private static final String TIPO_SIN_MODIFICADOR = "SinModificador";
    private static final String TIPO_LEGENDARIA = "Legendaria";
    private static final String TIPO_MEDICO = "Medico";
    private static final String TIPO_CARTA_UNIDA = "Carta Unida";
    private static final String TIPO_MORALE_BOOST = "Morale Boost";
    private static final String TIPO_ESPIA = "Espia";
    private static final String TIPO_AGIL = "Agil";

    public static Modificador crearModificador(String modificador, Jugador jugador, List<Seccion> secciones) {
        switch (modificador) {

            case TIPO_SIN_MODIFICADOR:
                return new SinModificador();

            case TIPO_LEGENDARIA:
                return new Legendaria();

            case TIPO_MEDICO:
                return new Medico(jugador);

            case TIPO_CARTA_UNIDA:
                return new Unida();

            case TIPO_MORALE_BOOST:
                return new MoraleBoostMod();

            case TIPO_ESPIA:
                return new Espia(jugador);

            case TIPO_AGIL:
                return new Agil(secciones);

            default:
                throw new ModificadorInexistenteException("El modificador ingresado no existe: " + modificador);
        }
    }
}

