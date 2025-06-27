package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Jugador;
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

        //aca cargo las propiedades de la ventana
        stage.setTitle("Gwent");
        primaryStage.setMinWidth(1280); // proporcion 9/16
        primaryStage.setMinHeight(720);

        Image icono = new Image("file:src/main/resources/imagenes/logo/logo_gwent.png");
        stage.getIcons().add(icono);

        mostrarPantallaBienvenida();

        primaryStage.show();
    }

    public void mostrarPantallaBienvenida(){
        double width = 1280, height = 720;
        if (primaryStage.getScene() != null) {
            width = primaryStage.getScene().getWidth();
            height = primaryStage.getScene().getHeight();
        }
        PantallaInicialView bienvenidaView = new PantallaInicialView(controller, this);
        Scene scene = new Scene(bienvenidaView, width, height);
        primaryStage.setScene(scene);
    }

    public void mostrarPantallaInicial() {
        double width = 1280, height = 720;
        if (primaryStage.getScene() != null) {
            width = primaryStage.getScene().getWidth();
            height = primaryStage.getScene().getHeight();
        }
        InicioView inicioView = new InicioView(controller, this);
        Scene scene = new Scene(inicioView, width, height);
        primaryStage.setScene(scene);
    }

    public void mostrarPantallaPreparacion() {
        double width = 1280, height = 720;
        if (primaryStage.getScene() != null) {
            width = primaryStage.getScene().getWidth();
            height = primaryStage.getScene().getHeight();
        }
        PreparacionView preparacionView = new PreparacionView(controller, this);
        Scene scene = new Scene(preparacionView, width, height);
        primaryStage.setScene(scene);
    }

    public void mostrarPantallaJuego() {
        double width = 1280, height = 720;
        if (primaryStage.getScene() != null) {
            width = primaryStage.getScene().getWidth();
            height = primaryStage.getScene().getHeight();
        }
        TableroView tableroView = new TableroView(controller, this);
        Scene scene = new Scene(tableroView, width, height);
        primaryStage.setScene(scene);
    }

    public void mostrarPantallaFinal(Jugador ganador) {
        double width = 1280, height = 720;
        if (primaryStage.getScene() != null) {
            width = primaryStage.getScene().getWidth();
            height = primaryStage.getScene().getHeight();
        }
        FinJuegoView finJuegoView = new FinJuegoView(ganador, this);
        Scene scene = new Scene(finJuegoView, width, height);
        primaryStage.setScene(scene);
    }

    /**
     * Devuelve el controlador de la aplicación.
     * Permite que las vistas accedan al controlador para operaciones como reiniciar el juego.
     */
    public GwentController getController() {
        return this.controller;
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
