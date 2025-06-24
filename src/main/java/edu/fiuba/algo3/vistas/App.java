package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.SystemInfo;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {
/*
    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }
*/
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Gwent");
        // Cargar el ícono (usando tu imagen)
        Image icono = new Image("file:src/main/resources/logo/logo_gwent.png"); // o la ruta que corresponda
        stage.getIcons().add(icono);
        // Cargar la imagen desde recursos o ruta absoluta
        Image imagen = new Image("file:src/main/resources/fondos/fondo_gwent.png");  // o "file:/ruta/completa.jpg"

        // Crear un ImageView
        ImageView imageView = new ImageView(imagen);
        //
        imageView.setFitWidth(1800);  // Ajustar ancho
        imageView.setFitHeight(780); // Ajustar alto
        imageView.setPreserveRatio(true);  // Mantener proporción

        //crear otra imagen
        Image imagenLogo = new Image("file:src/main/resources/logo/logo_gwent.png");
        ImageView imageViewLogo = new ImageView(imagenLogo);
        imageViewLogo.setFitWidth(400); // Ajustá el tamaño a gusto
        imageViewLogo.setPreserveRatio(true);
        //agrego funciones del logo
        // Escala original
        imageViewLogo.setScaleX(1);
        imageViewLogo.setScaleY(1);


        // Al pasar el mouse, agranda
        imageViewLogo.setOnMouseEntered(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), imageViewLogo);
            st.setToX(1.2);  // 20% más grande
            st.setToY(1.2);
            st.play();
        });
        // Al sacar el mouse, queda normal
        imageViewLogo.setOnMouseExited(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), imageViewLogo);
            st.setToX(1);
            st.setToY(1);
            st.play();
        });

        // label de continuar
        Label mensaje = new Label("Click para continuar");
        mensaje.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");

        VBox contenedorCentral = new VBox(80, imageViewLogo, mensaje);
        contenedorCentral.setAlignment(Pos.CENTER);

        //Label efecto
        // Agregamos animación de desvanecer/reaparecer
        FadeTransition fade = new FadeTransition(Duration.seconds(1), mensaje);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setCycleCount(FadeTransition.INDEFINITE);
        fade.setAutoReverse(true);
        fade.play();


        //// Funcionaaaa musicaaaaaaa
        Media media = new Media("file:src/main/resources/sonidos/musica_intro.mp3");
        AudioClip sonido = new AudioClip(media.getSource());
        sonido.setCycleCount(AudioClip.INDEFINITE);     /// Este es el loop infinito de la cancion
        sonido.play();

        StackPane root = new StackPane();
        root.getChildren().addAll(imageView, contenedorCentral); // fondo primero, luego lo centrado encima
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.sizeToScene(); // Ajusta la ventana al tamaño de la imagen
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

}