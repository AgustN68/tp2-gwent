package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.GwentController;
import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Jugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

public class PreparacionView extends ScrollPane {

    private GwentController controller;
    private GwentApp app;
    private Jugador jugadorActual;
    private List<Integer> cartasSeleccionadas = new ArrayList<>();
    private List<CartaView> vistasCartas = new ArrayList<>();
    private BorderPane mainLayout;

    public PreparacionView(GwentController controller, GwentApp app) {
        this.controller = controller;
        this.app = app;
        this.jugadorActual = controller.getJugador1(); // Comenzamos con el jugador 1

        // Creamos un BorderPane como layout principal
        mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(20));

        // Configuramos el ScrollPane (this)
        this.setContent(mainLayout);
        this.setFitToWidth(true);
        this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        this.setHbarPolicy(ScrollBarPolicy.NEVER);

        // Sección superior - Título y jugador actual
        Label tituloLabel = new Label("FASE DE PREPARACIÓN");
        tituloLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));

        Label jugadorLabel = new Label("Turno de: " + jugadorActual.getNombre());
        jugadorLabel.setFont(Font.font("Arial", 18));

        VBox headerBox = new VBox(10, tituloLabel, jugadorLabel);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(20));
        mainLayout.setTop(headerBox);

        // Sección central - Cartas en mano
        actualizarVistaMano();

        // Sección inferior - Botones de acción
        Button descartarButton = new Button("Descartar seleccionadas");
        descartarButton.setFont(Font.font("Arial", 16));
        descartarButton.setPrefSize(200, 50);
        descartarButton.setOnAction(e -> descartarCartas());

        Button saltarButton = new Button("No descartar cartas");
        saltarButton.setFont(Font.font("Arial", 16));
        saltarButton.setPrefSize(200, 50);
        saltarButton.setOnAction(e -> siguienteJugador());

        HBox actionBox = new HBox(20, descartarButton, saltarButton);
        actionBox.setAlignment(Pos.CENTER);
        actionBox.setPadding(new Insets(20));
        mainLayout.setBottom(actionBox);
    }

    private void actualizarVistaMano() {
        FlowPane cartasPane = new FlowPane();
        cartasPane.setHgap(15);
        cartasPane.setVgap(15);
        cartasPane.setAlignment(Pos.CENTER);
        cartasPane.setPadding(new Insets(20));

        vistasCartas.clear();

        // Mostrar las cartas del jugador actual
        List<Carta> cartasEnMano = jugadorActual.verMano();
        for (int i = 0; i < cartasEnMano.size(); i++) {
            Carta carta = cartasEnMano.get(i);
            final int posicion = i;

            CartaView cartaView = new CartaView(carta);
            cartaView.setStyle("-fx-border-color: gray; -fx-border-width: 2;");

            cartaView.setOnMouseClicked(e -> seleccionarCarta(posicion, cartaView));
            vistasCartas.add(cartaView);
            cartasPane.getChildren().add(cartaView);
        }

        // Instrucciones
        Label instruccionesLabel = new Label("Selecciona hasta 2 cartas para descartar");
        instruccionesLabel.setFont(Font.font("Arial", 16));

        VBox centerBox = new VBox(20, instruccionesLabel, cartasPane);
        centerBox.setAlignment(Pos.CENTER);

        // Configuramos un ScrollPane para permitir el desplazamiento vertical
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(centerBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false); // No ajustar al alto para permitir scroll
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Siempre mostrar la barra vertical
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // No mostrar barra horizontal
        scrollPane.setPrefViewportHeight(400); // Altura fija del viewport para forzar scroll

        // Asegurarse de que el ScrollPane pueda crecer para llenar el espacio disponible
        scrollPane.setMaxHeight(Double.MAX_VALUE);
        scrollPane.setMinHeight(300);

        mainLayout.setCenter(scrollPane);
    }

    private void seleccionarCarta(int posicion, CartaView cartaView) {
        if (cartasSeleccionadas.contains(posicion)) {
            // Deseleccionar carta
            cartasSeleccionadas.remove(Integer.valueOf(posicion));
            cartaView.setStyle("-fx-border-color: gray; -fx-border-width: 2;");
        } else if (cartasSeleccionadas.size() < 2) {
            // Seleccionar carta
            cartasSeleccionadas.add(posicion);
            cartaView.setStyle("-fx-border-color: gold; -fx-border-width: 3;");
        }
    }

    private void descartarCartas() {
        if (!cartasSeleccionadas.isEmpty()) {
            controller.descartarCartas(jugadorActual, cartasSeleccionadas);
            cartasSeleccionadas.clear();
        }
        siguienteJugador();
    }

    private void siguienteJugador() {
        if (jugadorActual == controller.getJugador1()) {
            // Cambiar al jugador 2
            jugadorActual = controller.getJugador2();
            cartasSeleccionadas.clear();
            actualizarVistaMano();
            ((Label)((VBox)mainLayout.getTop()).getChildren().get(1)).setText("Turno de: " + jugadorActual.getNombre());
        } else {
            // Ambos jugadores han terminado la fase de preparación
            app.mostrarPantallaJuego();
        }
    }
}
