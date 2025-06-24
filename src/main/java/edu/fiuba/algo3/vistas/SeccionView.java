package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

/**
 * Componente visual para representar una sección del tablero
 */
public class SeccionView extends VBox {

    private Seccion seccion;
    private String nombreSeccion;
    private String nombreJugador;

    public SeccionView(Seccion seccion, String nombreSeccion, String nombreJugador) {
        this.seccion = seccion;
        this.nombreSeccion = nombreSeccion;
        this.nombreJugador = nombreJugador;

        setPadding(new Insets(10));
        setSpacing(10);
        setAlignment(Pos.CENTER);

        actualizarVista();
    }

    public void actualizarVista() {
        getChildren().clear();

        // Etiqueta con nombre de la sección
        Label nombreLabel = new Label(nombreSeccion + " - " + nombreJugador);
        nombreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // Contenedor para las cartas
        FlowPane cartasPane = new FlowPane();
        cartasPane.setHgap(10);
        cartasPane.setVgap(10);
        cartasPane.setAlignment(Pos.CENTER);
        cartasPane.setPadding(new Insets(5));
        cartasPane.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-padding: 10px;");
        cartasPane.setMinHeight(150);
        cartasPane.setPrefWidth(800);

        // Agregar las cartas a la sección
        List<Unidad> cartas = seccion.getCartasUnidades();
        for (Unidad unidad : cartas) {
            CartaView cartaView = new CartaView(unidad);
            cartasPane.getChildren().add(cartaView);
        }

        // Mostrar el puntaje de la sección
        int puntajeSeccion = seccion.puntajeTotal().obtenerValor();
        Label puntajeLabel = new Label("Puntos: " + puntajeSeccion);
        puntajeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // Añadir componentes al contenedor principal
        getChildren().addAll(nombreLabel, cartasPane, puntajeLabel);
    }

    public Seccion getSeccion() {
        return seccion;
    }
}
