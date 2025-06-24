package edu.fiuba.algo3.vistas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import edu.fiuba.algo3.controllers.GwentController;

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

    public static void main(String[] args) {
        launch(args);
    }
}
