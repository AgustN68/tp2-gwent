package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Jugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class FinJuegoView extends VBox {

    private GwentApp app;
    private AudioClip sonido;

    public FinJuegoView(Jugador ganador, GwentApp app) {
        this.app = app;

        setPadding(new Insets(50));
        setSpacing(30);
        setAlignment(Pos.CENTER);

        try {
            setBackground(new Background(app.obtenerBackgroundImage("/imagenes/fondos/tablero.jpg")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //añado musica
        try {
            Media media = new Media(getClass().getResource("/sonidos/musica_ganador.wav").toExternalForm());
            sonido = new AudioClip(media.getSource());
            sonido.setCycleCount(AudioClip.INDEFINITE);
            sonido.play();
        } catch (Exception e) {
            System.out.println("Error al cargar o reproducir la música: " + e.getMessage());
        }
        // Título
        Label tituloLabel = new Label("¡FIN DEL JUEGO!");
        tituloLabel.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        tituloLabel.setTextFill(Color.WHITE);

        // Mensaje de ganador
        Label ganadorLabel = new Label("El ganador es: " + ganador.getNombre());
        ganadorLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        ganadorLabel.setTextFill(Color.WHITE);

        // Botón para jugar de nuevo
        Button reiniciarButton = new Button("Jugar de nuevo");
        reiniciarButton.setFont(Font.font("Arial", 16));
        reiniciarButton.setPrefSize(200, 50);
        reiniciarButton.setOnAction(e -> {
            this.sonido.stop();
            // Reiniciar el juego antes de mostrar la pantalla inicial
            app.getController().reiniciarJuego();
            app.mostrarPantallaInicial();
        });

        // Botón para salir
        Button salirButton = new Button("Salir del juego");
        salirButton.setFont(Font.font("Arial", 16));
        salirButton.setPrefSize(200, 50);
        salirButton.setOnAction(e ->   {
            this.sonido.stop();
            ((Stage) getScene().getWindow()).close();
        });

        // Añadir componentes a la vista
        getChildren().addAll(
                tituloLabel,
                ganadorLabel,
                reiniciarButton,
                salirButton
        );
    }
}
