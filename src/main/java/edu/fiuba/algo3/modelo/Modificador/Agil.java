package edu.fiuba.algo3.modelo.Modificador;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Seccion.Seccion;

import java.util.List;

public class Agil extends Modificador {
    private final List<Seccion> seccionesPermitidas;
    private Unidad unidad;

    public Agil(List<Seccion> secciones) {
        this.seccionesPermitidas = secciones;
    }

    @Override
    public void aplicarModificador() {
        this.unidad.actualizarSecciones(seccionesPermitidas);

    }
    public void setCarta(Unidad hechicero) {
        this.unidad = hechicero;
        this.aplicarModificador();
    }
}
