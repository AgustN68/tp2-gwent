package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.SystemInfo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
        stage.setTitle("Mi primera ventana");

        StackPane layout = new StackPane();

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

        Button boton = new Button();
        boton.setText("Texto del boton");
        layout.getChildren().add(boton);
        /*
        Label texto = new Label();
        texto.setText("Texto de la etiqueta");
        layout.getChildren().add(texto);
        /*
        TextField textField = new TextField();

        layout.getChildren().add(textField);

        TextArea textArea = new TextArea();
        textArea.setPrefRowCount(1);
        textArea.setPrefWidth(2);

        layout.getChildren().add(textArea);
        */
        Button boton2 = new Button();
        boton2.setText("Clickeame");

        MiBotonEventHandler miBotonEventHandler = new MiBotonEventHandler(boton2);

        boton2.setOnAction(miBotonEventHandler);

        layout.getChildren().add(boton2);



    }
    public static void main(String[] args) {
        launch();
    }

}