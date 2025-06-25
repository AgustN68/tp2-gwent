package edu.fiuba.algo3.vistas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import edu.fiuba.algo3.controllers.GwentController;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class GwentApp extends Application {

    private GwentController controller;
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.controller = new GwentController();
        this.controller.setApp(this); // Establecer la referencia al GwentApp en el controlador

        // Configuración básica de la ventana
        primaryStage.setTitle("Gwent - TP2");
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(768);

        // Mostrar la pantalla inicial para ingresar nombres
        mostrarPantallaInicial();

        primaryStage.show();
    }

    public void mostrarPantallaInicial() {
        InicioView inicioView = new InicioView(controller, this);
        Scene scene = new Scene(inicioView, 1024, 768);
        primaryStage.setScene(scene);
    }

    public void mostrarPantallaPreparacion() {
        PreparacionView preparacionView = new PreparacionView(controller, this);
        Scene scene = new Scene(preparacionView, 1024, 768);
        primaryStage.setScene(scene);
    }

    public void mostrarPantallaJuego() {
        TableroView tableroView = new TableroView(controller, this);
        Scene scene = new Scene(tableroView, 1024, 768);
        primaryStage.setScene(scene);
    }

    public void mostrarPantallaFinal(String ganador) {
        FinJuegoView finJuegoView = new FinJuegoView(ganador, this);
        Scene scene = new Scene(finJuegoView, 1024, 768);
        primaryStage.setScene(scene);
    }

    public BackgroundImage obtenerBackgroundImage(String direccionImagen) throws FileNotFoundException {
        Image fondoImage;
        InputStream is = getClass().getResourceAsStream(direccionImagen);
        if (is == null) {
            throw new FileNotFoundException(direccionImagen);
        }
        fondoImage = new Image(is);

        BackgroundImage fondo = new BackgroundImage(
                fondoImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,true,true,false, true)
        );
        return fondo;
    }

    public StackPane crearIcono(String direccionImagen, Double size) throws FileNotFoundException {
        StackPane pane = new StackPane();
        pane.setMinSize(size, size);
        pane.setPrefSize(size, size);
        pane.setMaxSize(size, size);

        BackgroundImage bgImg = obtenerBackgroundImage(direccionImagen);
        BackgroundSize bgSize = new BackgroundSize(
                size, size, false, false, false, false
        );
        BackgroundImage fixedBgImg = new BackgroundImage(
                bgImg.getImage(),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bgSize
        );
        pane.setBackground(new Background(fixedBgImg));

        return pane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
