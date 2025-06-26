package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Jugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FinJuegoView extends VBox {

    private GwentApp app;

    public FinJuegoView(Jugador ganador, GwentApp app) {
        this.app = app;

        setPadding(new Insets(50));
        setSpacing(30);
        setAlignment(Pos.CENTER);

        // Título
        Label tituloLabel = new Label("¡FIN DEL JUEGO!");
        tituloLabel.setFont(Font.font("Arial", FontWeight.BOLD, 48));

        // Mensaje de ganador
        Label ganadorLabel = new Label("El ganador es: " + ganador.getNombre());
        ganadorLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));

        // Botón para jugar de nuevo
        Button reiniciarButton = new Button("Jugar de nuevo");
        reiniciarButton.setFont(Font.font("Arial", 16));
        reiniciarButton.setPrefSize(200, 50);
        reiniciarButton.setOnAction(e -> app.mostrarPantallaInicial());

        // Botón para salir
        Button salirButton = new Button("Salir del juego");
        salirButton.setFont(Font.font("Arial", 16));
        salirButton.setPrefSize(200, 50);
        salirButton.setOnAction(e -> ((Stage) getScene().getWindow()).close());

        // Añadir componentes a la vista
        getChildren().addAll(
                tituloLabel,
                ganadorLabel,
                reiniciarButton,
                salirButton
        );
    }
}
