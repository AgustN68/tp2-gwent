package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.GwentController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class InicioView extends VBox {

    private GwentController controller;
    private GwentApp app;
    private TextField nombreJugador1Field;
    private TextField nombreJugador2Field;

    public InicioView(GwentController controller, GwentApp app) {
        this.controller = controller;
        this.app = app;

        setPadding(new Insets(50));
        setSpacing(20);
        setAlignment(Pos.CENTER);

        // Título
        Label tituloLabel = new Label("GWENT");
        tituloLabel.setFont(Font.font("Arial", FontWeight.BOLD, 48));

        // Formulario para jugador 1
        Label jugador1Label = new Label("Nombre del Jugador 1:");
        jugador1Label.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        nombreJugador1Field = new TextField();
        nombreJugador1Field.setMaxWidth(300);
        nombreJugador1Field.setPromptText("Ingrese nombre del Jugador 1");

        // Formulario para jugador 2
        Label jugador2Label = new Label("Nombre del Jugador 2:");
        jugador2Label.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        nombreJugador2Field = new TextField();
        nombreJugador2Field.setMaxWidth(300);
        nombreJugador2Field.setPromptText("Ingrese nombre del Jugador 2");

        // Botón para iniciar
        Button iniciarButton = new Button("Iniciar Juego");
        iniciarButton.setFont(Font.font("Arial", 16));
        iniciarButton.setPrefSize(200, 50);
        iniciarButton.setOnAction(e -> iniciarJuego());

        // Añadir componentes a la vista
        getChildren().addAll(
                tituloLabel,
                jugador1Label,
                nombreJugador1Field,
                jugador2Label,
                nombreJugador2Field,
                iniciarButton
        );
    }

    private void iniciarJuego() {
        String nombreJ1 = nombreJugador1Field.getText().isEmpty() ? "Jugador 1" : nombreJugador1Field.getText();
        String nombreJ2 = nombreJugador2Field.getText().isEmpty() ? "Jugador 2" : nombreJugador2Field.getText();

        // Iniciar el juego con los nombres (los mazos se cargan internamente)
        controller.iniciarJuego(nombreJ1, nombreJ2);

        // Pasar a la fase de preparación
        app.mostrarPantallaPreparacion();
    }
}
