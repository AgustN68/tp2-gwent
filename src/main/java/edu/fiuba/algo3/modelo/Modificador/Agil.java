package edu.fiuba.algo3.modelo.Modificador;

import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Seccion.Seccion;

import java.util.List;

public class Agil extends Modificador {
    private final List<Seccion> seccionesPermitidas;
    private Unidad unidad;
    private String descripcion = "Agil: Se puede colocarse en la fila de combate a distancia o cuerpo a cuerpo. No se pueden mover una vez colocadas.";

    public Agil(List<Seccion> secciones) {
        this.seccionesPermitidas = secciones;
    }

    @Override
    public void aplicarModificador() {
        this.unidad.actualizarSecciones(seccionesPermitidas);

    }

    @Override
    public void setCarta(Unidad unidad) {
        this.unidad = unidad;
        this.aplicarModificador();
    }

    @Override
    public String obtenerDescripcion() {
        return this.descripcion;
    }
}
