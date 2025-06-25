package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.awt.*;
import java.util.List;

/**
 * Componente visual para representar una sección del tablero
 */
public class SeccionView extends HBox {

    private Seccion seccion;
    private String nombreSeccion;
    private String nombreJugador;
    private GwentApp app;

    public SeccionView(Seccion seccion, String nombreSeccion, String nombreJugador, GwentApp app) {
        this.seccion = seccion;
        this.nombreSeccion = nombreSeccion;
        this.nombreJugador = nombreJugador;
        this.app = app;

        setPadding(new Insets(1));
        setSpacing(5);
        setAlignment(Pos.CENTER_LEFT);

        actualizarVista();
    }

    public void actualizarVista() {
        getChildren().clear();



        // Contenedor para las cartas
        double paddingCartasPane = 2;
        HBox cartasPane = new HBox(2);
        cartasPane.setAlignment(Pos.CENTER);
        cartasPane.setPadding(new Insets(paddingCartasPane));
        cartasPane.setStyle("-fx-border-color: gray; -fx-border-width: 1px;");
        cartasPane.setMinHeight(CartaView.getAlto()+2*paddingCartasPane+2);

        HBox.setHgrow(cartasPane, Priority.ALWAYS);


        // Agregar las cartas a la sección
        List<Unidad> cartas = seccion.getCartasUnidades();
        for (Unidad unidad : cartas) {
            CartaView cartaView = new CartaView(unidad, app, false);
            cartasPane.getChildren().add(cartaView);
        }

        // Mostrar el puntaje de la sección
        int puntajeSeccion = seccion.puntajeTotal().obtenerValor();
        Label puntajeLabel = new Label("Puntos: " + puntajeSeccion);
        puntajeLabel.setAlignment(Pos.CENTER);
        puntajeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        puntajeLabel.setStyle("-fx-text-fill: #FFFFFF;");

        // Mostrar nombre de la sección
        Label nombreSeccionLabel = new Label(nombreSeccion);
        nombreSeccionLabel.setAlignment(Pos.CENTER);
        nombreSeccionLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        nombreSeccionLabel.setStyle("-fx-text-fill: #FFFFFF;");
        nombreSeccionLabel.setWrapText(true);
        nombreSeccionLabel.setMaxWidth(80);

        VBox informacionPane = new VBox(10);
        informacionPane.setAlignment(Pos.CENTER);
        informacionPane.setPrefWidth(80);
        informacionPane.getChildren().addAll(puntajeLabel, nombreSeccionLabel);
        // Añadir componentes al contenedor principal
        getChildren().addAll(informacionPane, cartasPane);

        setOnMouseEntered(e -> {
            setViewOrder(-1.0);
        });
        setOnMouseExited(e -> {
            setViewOrder(0.0);
        });
    }

    public Seccion getSeccion() {
        return seccion;
    }
}
