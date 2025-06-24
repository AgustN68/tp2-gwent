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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class TableroView extends BorderPane {

    private GwentController controller;
    private GwentApp app;
    private Integer cartaSeleccionada = null;
    private CartaView cartaSeleccionadaView = null;

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

        Label puntajeJ1Label = new Label(controller.getJugador1().getNombre() + ": " +
                controller.getJugador1().obtenerPuntaje().obtenerValor() + " puntos | " +
                controller.getJugador1().rondasGanadas() + " rondas ganadas");
        puntajeJ1Label.setFont(Font.font("Arial", 16));

        Label puntajeJ2Label = new Label(controller.getJugador2().getNombre() + ": " +
                controller.getJugador2().obtenerPuntaje().obtenerValor() + " puntos | " +
                controller.getJugador2().rondasGanadas() + " rondas ganadas");
        puntajeJ2Label.setFont(Font.font("Arial", 16));

        Label turnoLabel = new Label("Turno de: " + controller.getJugadorActual().getNombre());
        turnoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        VBox cabeceraBox = new VBox(10, tituloLabel, puntajeJ1Label, puntajeJ2Label, turnoLabel);
        cabeceraBox.setAlignment(Pos.CENTER);
        cabeceraBox.setPadding(new Insets(5));
        setTop(cabeceraBox);
    }

    private void configurarTablero() {
        // Contenedor principal para el tablero
        VBox tableroBox = new VBox(10);
        tableroBox.setAlignment(Pos.CENTER);
        tableroBox.setPadding(new Insets(5));

        Jugador jugador1 = controller.getJugador1();
        Jugador jugador2 = controller.getJugador2();
        Jugador jugadorActual = controller.getJugadorActual();

        // Mostrar la mano del jugador 2 (rival)
        Label manoJ2Label = new Label("MANO JUGADOR " + jugador2.getNombre());
        manoJ2Label.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        HBox manoJ2Box = new HBox(10);
        manoJ2Box.setAlignment(Pos.CENTER);
        manoJ2Box.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-padding: 10px;");
        manoJ2Box.setMinHeight(60);
        manoJ2Box.setPrefWidth(800);

        // Solo mostrar el número de cartas del rival, no las cartas específicas
        Label cartasJ2Label = new Label("Cartas en mano: " + jugador2.verMano().size());
        cartasJ2Label.setFont(Font.font("Arial", 14));
        manoJ2Box.getChildren().add(cartasJ2Label);

        // Crear las vistas para cada sección del tablero
        List<Seccion> seccionesJ1 = jugador1.getTablero().getSecciones();
        List<Seccion> seccionesJ2 = jugador2.getTablero().getSecciones();

        // Asegurarnos de que tenemos todas las secciones necesarias y las añadimos en el orden correcto
        if (seccionesJ1.size() >= 3 && seccionesJ2.size() >= 3) {
            // Crear las vistas de las secciones, permitiendo hacer clic en ellas si hay una carta seleccionada
            SeccionView asedioJ2View = new SeccionView(seccionesJ2.get(2), "ASEDIO", jugador2.getNombre());
            SeccionView distanciaJ2View = new SeccionView(seccionesJ2.get(1), "DISTANCIA", jugador2.getNombre());
            SeccionView cuerpoACuerpoJ2View = new SeccionView(seccionesJ2.get(0), "CUERPO A CUERPO", jugador2.getNombre());

            SeccionView cuerpoACuerpoJ1View = new SeccionView(seccionesJ1.get(0), "CUERPO A CUERPO", jugador1.getNombre());
            SeccionView distanciaJ1View = new SeccionView(seccionesJ1.get(1), "DISTANCIA", jugador1.getNombre());
            SeccionView asedioJ1View = new SeccionView(seccionesJ1.get(2), "ASEDIO", jugador1.getNombre());

            // Si hay una carta seleccionada y es el turno del jugador 1, permitir colocar en sus secciones
            if (cartaSeleccionada != null && jugadorActual == jugador1) {
                configurarEventosSeccion(cuerpoACuerpoJ1View, seccionesJ1.get(0));
                configurarEventosSeccion(distanciaJ1View, seccionesJ1.get(1));
                configurarEventosSeccion(asedioJ1View, seccionesJ1.get(2));
            }

            // Si hay una carta seleccionada y es el turno del jugador 2, permitir colocar en sus secciones
            if (cartaSeleccionada != null && jugadorActual == jugador2) {
                configurarEventosSeccion(cuerpoACuerpoJ2View, seccionesJ2.get(0));
                configurarEventosSeccion(distanciaJ2View, seccionesJ2.get(1));
                configurarEventosSeccion(asedioJ2View, seccionesJ2.get(2));
            }

            // Añadir las secciones al tablero en el orden requerido
            tableroBox.getChildren().addAll(
                    manoJ2Label,
                    manoJ2Box,
                    new Label("SECCION ASEDIO JUGADOR " + jugador2.getNombre()),
                    asedioJ2View,
                    new Label("SECCION DISTANCIA JUGADOR " + jugador2.getNombre()),
                    distanciaJ2View,
                    new Label("SECCION CUERPO A CUERPO JUGADOR " + jugador2.getNombre()),
                    cuerpoACuerpoJ2View,
                    new Label("SECCION CUERPO A CUERPO JUGADOR " + jugador1.getNombre()),
                    cuerpoACuerpoJ1View,
                    new Label("SECCION DISTANCIA JUGADOR " + jugador1.getNombre()),
                    distanciaJ1View,
                    new Label("SECCION ASEDIO JUGADOR " + jugador1.getNombre()),
                    asedioJ1View
            );
        }

        // Crear un ScrollPane para poder ver todo el contenido
        ScrollPane scrollPane = new ScrollPane(tableroBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(500); // Altura ajustable según necesidad
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        setCenter(scrollPane);
    }

    private void configurarEventosSeccion(SeccionView seccionView, Seccion seccion) {
        // Destacar visualmente que esta sección es seleccionable
        seccionView.setStyle("-fx-border-color: lightblue; -fx-border-width: 2px; -fx-background-color: rgba(173, 216, 230, 0.2);");

        seccionView.setOnMouseClicked(e -> {
            if (cartaSeleccionada != null) {
                try {
                    // Intenta jugar la carta en esta sección
                    controller.jugarCartaEnSeccion(cartaSeleccionada, seccion);

                    // Resetear la selección
                    cartaSeleccionada = null;
                    cartaSeleccionadaView = null;

                    // Actualizar toda la vista
                    actualizarVista();
                } catch (Exception ex) {
                    // Si hay un error (por ejemplo, la carta no puede jugarse en esta sección),
                    // mostrar un mensaje de error (podría mejorarse con un diálogo)
                    System.out.println("Error al jugar carta: " + ex.getMessage());
                }
            }
        });
    }

    private void configurarManoYAcciones() {
        VBox contenedorInferior = new VBox(10);
        contenedorInferior.setAlignment(Pos.CENTER);
        contenedorInferior.setPadding(new Insets(5));

        // Título para la mano del jugador
        Label manoTituloLabel = new Label("TU MANO");
        manoTituloLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Mensaje de instrucción
        Label instruccionLabel = new Label(cartaSeleccionada == null ?
                "Selecciona una carta para jugar" :
                "Carta seleccionada. Ahora elige una sección donde colocarla");
        instruccionLabel.setFont(Font.font("Arial", 14));

        // Mostrar las cartas en la mano del jugador actual
        HBox manoBox = new HBox(10);
        manoBox.setAlignment(Pos.CENTER);
        manoBox.setPadding(new Insets(5));

        List<Carta> cartasEnMano = controller.getJugadorActual().verMano();
        for (int i = 0; i < cartasEnMano.size(); i++) {
            Carta carta = cartasEnMano.get(i);
            final int posicion = i;

            CartaView cartaView = new CartaView(carta);

            // Si esta carta ya está seleccionada, resaltarla
            if (cartaSeleccionada != null && cartaSeleccionada == posicion) {
                cartaView.setStyle("-fx-border-color: gold; -fx-border-width: 3;");
                cartaSeleccionadaView = cartaView;
            }

            cartaView.setOnMouseClicked(e -> {
                // Si ya hay una carta seleccionada, deseleccionarla
                if (cartaSeleccionadaView != null) {
                    cartaSeleccionadaView.setStyle("-fx-border-color: black; -fx-border-width: 1;");
                }

                // Si seleccionamos la misma carta, la deseleccionamos
                if (cartaSeleccionada != null && cartaSeleccionada == posicion) {
                    cartaSeleccionada = null;
                    cartaSeleccionadaView = null;
                } else {
                    // Seleccionar la nueva carta
                    cartaSeleccionada = posicion;
                    cartaSeleccionadaView = cartaView;
                    cartaView.setStyle("-fx-border-color: gold; -fx-border-width: 3;");
                }

                // Actualizar solo la parte inferior para reflejar la selección
                setBottom(null);
                configurarManoYAcciones();
                // Actualizar también el tablero para mostrar las secciones seleccionables
                setCenter(null);
                configurarTablero();
            });

            manoBox.getChildren().add(cartaView);
        }

        // Botones de acción
        Button pasarButton = new Button("Pasar");
        pasarButton.setFont(Font.font("Arial", 16));
        pasarButton.setPrefSize(150, 50);
        pasarButton.setOnAction(e -> {
            // Limpiar selección antes de pasar
            cartaSeleccionada = null;
            cartaSeleccionadaView = null;
            controller.pasarTurno();
            actualizarVista();
        });

        Button cancelarButton = new Button("Cancelar selección");
        cancelarButton.setFont(Font.font("Arial", 16));
        cancelarButton.setPrefSize(200, 50);
        cancelarButton.setDisable(cartaSeleccionada == null);
        cancelarButton.setOnAction(e -> {
            cartaSeleccionada = null;
            cartaSeleccionadaView = null;
            // Actualizar solo la parte inferior
            setBottom(null);
            configurarManoYAcciones();
            // Actualizar también el tablero para quitar las secciones seleccionables
            setCenter(null);
            configurarTablero();
        });

        HBox accionesBox = new HBox(20, pasarButton, cancelarButton);
        accionesBox.setAlignment(Pos.CENTER);
        accionesBox.setPadding(new Insets(5));

        contenedorInferior.getChildren().addAll(
                manoTituloLabel,
                instruccionLabel,
                manoBox,
                accionesBox
        );

        setBottom(contenedorInferior);
    }

    private void actualizarVista() {
        // Actualizar la cabecera
        configurarCabecera();

        // Volver a configurar el tablero y la mano/acciones
        configurarTablero();
        configurarManoYAcciones();
    }
}
