package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Jugador;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import java.io.FileNotFoundException;

public class JugadorView extends BorderPane {

    Jugador jugador;
    GwentApp app;
    Integer jugadorId;

    public JugadorView(GwentApp app, Jugador jugador, Integer jugadorId) {
        this.jugador = jugador;
        this.app = app;
        this.jugadorId = jugadorId;

        setStyle("-fx-background-color: rgba(120, 120, 120, 0.5); -fx-padding: 10; -fx-border-color: #000; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5");

        configurarArriba();

        configurarAbajo();

        configurarDerecha();
    }

    private void configurarDerecha() {
        StackPane puntajePane;
        try {
            puntajePane = app.crearIcono("/imagenes/iconos/puntos_jugador_" + jugadorId + ".png", 20.0);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Label puntajeLabel = new Label(String.valueOf(jugador.obtenerPuntaje().obtenerValor()));
        puntajeLabel.setFont(Font.font("Arial", 16));
        puntajeLabel.setStyle("-fx-text-fill: #FFFFFF;");

        puntajePane.getChildren().add(puntajeLabel);

        setRight(puntajePane);
    }

    private void configurarAbajo() {
        Label nombreLabel = new Label(jugador.getNombre());
        nombreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        nombreLabel.setStyle("-fx-text-fill: #FFFFFF;");

        setBottom(nombreLabel);
    }

    private void configurarArriba() {
        // Cartas restantes
        HBox cartasYVidasBox = new HBox();

        StackPane cartasPane;
        try {
            cartasPane = app.crearIcono("/imagenes/iconos/cartas_restantes.png", 20.0);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Label cartasRestantesLabel = new Label(String.valueOf(jugador.verMano().size()));
        cartasRestantesLabel.setFont(Font.font("Arial", 16));
        cartasRestantesLabel.setStyle("-fx-text-fill: #FFFFFF;");

        StackPane vidasPane;
        try {
            vidasPane = app.crearIcono("/imagenes/iconos/gema_prendida.png", 20.0);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Label rondasGanadasLabel = new Label(String.valueOf(jugador.rondasGanadas()));
        rondasGanadasLabel.setFont(Font.font("Arial", 16));
        rondasGanadasLabel.setStyle("-fx-text-fill: #FFFFFF;");

        Region espacio = new Region();
        espacio.setMinWidth(10); // Espacio entre cartas y rondas ganadas

        cartasYVidasBox.getChildren().addAll(cartasPane, cartasRestantesLabel, espacio, vidasPane, rondasGanadasLabel);

        setTop(cartasYVidasBox);
    }

    public void actualizarTurno(String nombreJugadorActual) {
        // Actualizar el estilo del jugador actual
        if (jugador.getNombre().equals(nombreJugadorActual)) {
            setStyle("-fx-background-color: rgba(255, 215, 0, 0.5); -fx-padding: 10; -fx-border-color: #000; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5");
            // Transición de resaltado al cambiar el turno
            FadeTransition ft = new FadeTransition(Duration.seconds(1.0), this);
            ft.setFromValue(1.0);
            ft.setToValue(0.5);
            ft.setCycleCount(8); // cada ciclo es un medio
            ft.setRate(4); // ciclos por segundo
            ft.setAutoReverse(true);
            ft.setOnFinished(ev -> setOpacity(1.0));
            ft.play();
        } else {
            setStyle("-fx-background-color: rgba(120, 120, 120, 0.5); -fx-padding: 10; -fx-border-color: #000; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5");
            setOpacity(1.0);
        }
    }
}
