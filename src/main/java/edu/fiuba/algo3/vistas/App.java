package edu.fiuba.algo3.vistas;

import javafx.application.Application;

import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;

/**
 * JavaFX App - Punto de entrada principal de la aplicación
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Iniciar la aplicación Gwent
        GwentApp gwentApp = new GwentApp();
        gwentApp.start(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}