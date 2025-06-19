package edu.fiuba.algo3.modelo.Factory;

import edu.fiuba.algo3.modelo.Carta.Especial.*;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import edu.fiuba.algo3.modelo.Tablero;
import java.util.List;

public class EspecialFactory {

    public static Especial crearEspecial(
            String tipo,
            String nombre,
            String descripcion,
            Tablero tablero1,
            Tablero tablero2,
            List<Seccion> seccionesAfectadas
    ) {

        Especial nuevaEspecial;
        String DESPEJADO = "Tiempo Despejado";

        switch (tipo) {
            case "Tierra arrasada":
                nuevaEspecial = new TierraArrasada();
                break;

            case "Morale Boost":
                nuevaEspecial = new MoraleBoost();
                break;

            case "Clima":
                if (nombre.equals(DESPEJADO)) {
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
