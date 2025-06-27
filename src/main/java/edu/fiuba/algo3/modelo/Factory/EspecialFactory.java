package edu.fiuba.algo3.modelo.Factory;

import edu.fiuba.algo3.modelo.Carta.Especial.*;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima.Clima;
import edu.fiuba.algo3.modelo.Carta.Especial.Clima.Despejado;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Tablero;
import java.util.List;

public class EspecialFactory {

    private static final String TIPO_TIERRA_ARRASADA = "Tierra arrasada";
    private static final String TIPO_MORALE_BOOST = "Morale Boost";
    private static final String TIPO_CLIMA = "Clima";
    private static final String NOMBRE_CLIMA_DESPEJADO = "Tiempo despejado";

    public static Especial crearEspecial(
            String tipo,
            String nombre,
            String descripcion,
            Tablero tablero1,
            Tablero tablero2,
            List<Seccion> seccionesAfectadas
    ) {

        Especial nuevaEspecial;

        switch (tipo) {
            case TIPO_TIERRA_ARRASADA:
                nuevaEspecial = new TierraArrasada();
                break;

            case TIPO_MORALE_BOOST:
                nuevaEspecial = new MoraleBoost();
                break;

            case TIPO_CLIMA:
                if (nombre.equals(NOMBRE_CLIMA_DESPEJADO)) {
                    nuevaEspecial = new Despejado(tablero1, tablero2);
                } else {
                    nuevaEspecial = new Clima(seccionesAfectadas);
                }
                break;


            default:
                throw new RuntimeException("El especial ingresado no existe: " + tipo);

        }

        nuevaEspecial.setNombre(nombre);
        nuevaEspecial.setDescripcion(descripcion);

        return nuevaEspecial;

    }
}
