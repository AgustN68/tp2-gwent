package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.GwentController;
import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class TableroView extends BorderPane {

    private GwentController controller;
    private GwentApp app;

    public TableroView(GwentController controller, GwentApp app) {
        this.controller = controller;
        this.app = app;

        setPadding(new Insets(10));

        // Cabecera - información del juego
        configurarCabecera();

        // Centro - tablero de juego
        configurarTablero();

        // Parte inferior - mano del jugador actual y botones de acción
        configurarManoYAcciones();
    }

    private void configurarCabecera() {
        Label tituloLabel = new Label("GWENT - RONDA " + controller.getRondaActual());
        tituloLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        Label turnoLabel = new Label("Turno de: " + controller.getJugadorActual().getNombre());
        turnoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        VBox cabeceraBox = new VBox(10, tituloLabel, turnoLabel);
        cabeceraBox.setAlignment(Pos.CENTER);
        cabeceraBox.setPadding(new Insets(5));
        setTop(cabeceraBox);
    }

    private void configurarTablero() {
        // Contenedor principal para el tablero
        VBox tableroBox = new VBox(2);
        tableroBox.setAlignment(Pos.CENTER);
        tableroBox.setPadding(new Insets(0));

        Jugador jugador1 = controller.getJugador1();
        Jugador jugador2 = controller.getJugador2();

        // Crear las vistas para cada sección del tablero
        List<Seccion> seccionesJ1 = jugador1.getTablero().getSecciones();
        List<Seccion> seccionesJ2 = jugador2.getTablero().getSecciones();

        // Asegurarnos de que tenemos todas las secciones necesarias y las añadimos en el orden correcto
        if (seccionesJ1.size() >= 3 && seccionesJ2.size() >= 3) {
            // Añadir las secciones al tablero en el orden requerido
            tableroBox.getChildren().addAll(
                    new SeccionView(seccionesJ2.get(2), "ASEDIO", jugador2.getNombre()),
                    new SeccionView(seccionesJ2.get(1), "DISTANCIA", jugador2.getNombre()),
                    new SeccionView(seccionesJ2.get(0), "CUERPO A CUERPO", jugador2.getNombre()),
                    new SeccionView(seccionesJ1.get(0), "CUERPO A CUERPO", jugador1.getNombre()),
                    new SeccionView(seccionesJ1.get(1), "DISTANCIA", jugador1.getNombre()),
                    new SeccionView(seccionesJ1.get(2), "ASEDIO", jugador1.getNombre())
            );
        }

        setCenter(tableroBox);
    }

    private void configurarManoYAcciones() {
        HBox contenedorInferior = new HBox(5);
        contenedorInferior.setAlignment(Pos.CENTER_LEFT);
        contenedorInferior.setPadding(new Insets(5));

        // Informacion
        HBox informacionBox = new HBox(10);
        informacionBox.setAlignment(Pos.CENTER);

        // para j1
        VBox informacionJ1Box = new VBox(5);

        Label nombreJ1Label = new Label(controller.getJugador1().getNombre());
        nombreJ1Label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Label cartasRestantesJ1Label = new Label("Cartas: " + controller.getJugador1().verMano().size());
        cartasRestantesJ1Label.setFont(Font.font("Arial", 16));

        Label puntajeJ1Label = new Label("Puntaje: " + controller.getJugador1().obtenerPuntaje().obtenerValor());
        puntajeJ1Label.setFont(Font.font("Arial", 16));

        Label rondasGanadasJ1Label = new Label("Rondas ganadas: " + controller.getJugador1().rondasGanadas());
        rondasGanadasJ1Label.setFont(Font.font("Arial", 16));

        informacionJ1Box.getChildren().addAll(nombreJ1Label, cartasRestantesJ1Label, puntajeJ1Label, rondasGanadasJ1Label);

        // para j2
        VBox informacionJ2Box = new VBox(5);

        Label nombreJ2Label = new Label(controller.getJugador2().getNombre());
        nombreJ2Label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Label cartasRestantesJ2Label = new Label("Cartas: " + controller.getJugador2().verMano().size());
        cartasRestantesJ2Label.setFont(Font.font("Arial", 16));

        Label puntajeJ2Label = new Label("Puntaje: " + controller.getJugador2().obtenerPuntaje().obtenerValor());
        puntajeJ2Label.setFont(Font.font("Arial", 16));

        Label rondasGanadasJ2Label = new Label("Rondas ganadas: " + controller.getJugador2().rondasGanadas());
        rondasGanadasJ2Label.setFont(Font.font("Arial", 16));

        informacionJ2Box.getChildren().addAll(nombreJ2Label, cartasRestantesJ2Label, puntajeJ2Label, rondasGanadasJ2Label);


        informacionBox.getChildren().addAll(informacionJ1Box, informacionJ2Box);

        // Mostrar las cartas en la mano del jugador actual
        HBox manoBox = new HBox(2);
        manoBox.setAlignment(Pos.CENTER);
        manoBox.setPadding(new Insets(5));
        HBox.setHgrow(manoBox, Priority.ALWAYS);

        List<Carta> cartasEnMano = controller.getJugadorActual().verMano();
        for (int i = 0; i < cartasEnMano.size(); i++) {
            Carta carta = cartasEnMano.get(i);
            final int posicion = i;

            CartaView cartaView = new CartaView(carta);
            cartaView.setOnMouseClicked(e -> {
                controller.jugarCarta(posicion);
                actualizarVista();
            });

            manoBox.getChildren().add(cartaView);
        }

        // Botones de acción
        Button pasarButton = new Button("Pasar");
        pasarButton.setFont(Font.font("Arial", 16));
        pasarButton.setPrefSize(150, 50);
        pasarButton.setOnAction(e -> {
            controller.pasarTurno();
            actualizarVista();
        });

        contenedorInferior.getChildren().addAll(informacionBox, manoBox, pasarButton);

        setBottom(contenedorInferior);
    }

    // Método para actualizar la vista cuando cambie el estado del juego
    public void actualizarVista() {
        // Eliminar todos los nodos actuales
        getChildren().clear();

        // Volver a configurar la vista
        configurarCabecera();
        configurarTablero();
        configurarManoYAcciones();
    }
}
