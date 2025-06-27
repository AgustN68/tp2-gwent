package edu.fiuba.algo3.vistas;
import edu.fiuba.algo3.controllers.GwentController;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.util.Duration;
import java.io.FileNotFoundException;

public class PantallaInicialView extends StackPane {

    private GwentController controller;
    private GwentApp app;
    private Scene escena;

    private AudioClip sonido;

        public PantallaInicialView(GwentController controller, GwentApp app) {
            try {
                setBackground(new Background(app.obtenerBackgroundImage("/imagenes/fondos/fondo_gwent2.jpg")));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            // Logo
            Image logo = new Image("file:src/main/resources/imagenes/logo/logo_gwent.png", true);
            ImageView logoView = new ImageView(logo);
            logoView.setFitWidth(400);
            logoView.setPreserveRatio(true);

            //añado musica
            try {
                Media media = new Media(getClass().getResource("/sonidos/musica_intro.mp3").toExternalForm());
                sonido = new AudioClip(media.getSource());
                sonido.setCycleCount(AudioClip.INDEFINITE);
                sonido.play();
            } catch (Exception e) {
                System.out.println("Error al cargar o reproducir la música: " + e.getMessage());
            }

            //efecto desvanecimiento
            this.setOpacity(1.0);

            efectoMouse(logoView);

            logoView.setOnMouseClicked(event -> {
                FadeTransition fadeOut = new FadeTransition(Duration.millis(800), this);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setOnFinished(e -> {
                    detenerSonido(sonido);
                    app.mostrarPantallaInicial();
                });
                fadeOut.play();
            });

            Label mensaje = mensajeParaContinuar();

            VBox centro = new VBox(50, logoView, mensaje);
            centro.setAlignment(Pos.CENTER);

            this.getChildren().add( centro);
        }

        public Label mensajeParaContinuar(){
            Label mensaje = new Label("Click en el logo para continuar");
            mensaje.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");

            FadeTransition fade = new FadeTransition(Duration.seconds(1), mensaje);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setCycleCount(FadeTransition.INDEFINITE);
            fade.setAutoReverse(true);
            fade.play();
            return mensaje;
        }

        public void efectoMouse(ImageView logoView){
            logoView.setOnMouseEntered(event -> {
                ScaleTransition st = new ScaleTransition(Duration.millis(200), logoView);
                st.setToX(1.2);
                st.setToY(1.2);
                st.play();
            });

            logoView.setOnMouseExited(event -> {
                ScaleTransition st = new ScaleTransition(Duration.millis(200), logoView);
                st.setToX(1);
                st.setToY(1);
                st.play();
            });
        }
        public void detenerSonido(AudioClip sonido){
            if(sonido != null){
                sonido.stop();
            }
        }
}
