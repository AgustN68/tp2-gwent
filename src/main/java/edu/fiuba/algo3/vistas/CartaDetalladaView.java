package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileNotFoundException;

public class CartaDetalladaView extends VBox {

    private static final String FUENTE = "serif";

    public CartaDetalladaView(Carta carta, GwentApp app) {
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(200, 0, 0, 0));

        CartaView cartaView = new CartaView(carta, app, false, true);
        cartaView.setScaleX(4.0);
        cartaView.setScaleY(4.0);
        cartaView.setViewOrder(-1.0);
        getChildren().add(cartaView);


        StackPane infoPane = new StackPane();
        infoPane.setMinWidth(240);
        infoPane.setPrefWidth(240);
        infoPane.setMaxWidth(240);
        infoPane.setMinHeight(240);
        infoPane.setTranslateY(130);
        try {
            infoPane.setBackground(new Background(app.obtenerBackgroundImage("/imagenes/fondos/zocalo.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        infoPane.setStyle(
                "-fx-padding: 5;" +
                "-fx-border-color: #000;" +
                "-fx-border-width: 0px 5px 5px 5px;" +
                "-fx-border-radius: 5;"
        );

        VBox labelsBox = new VBox(10);
        labelsBox.setPadding(new Insets(10, 0, 0, 0));


        // Nombre
        Label nombreLabel = new Label(carta.getNombre());
        nombreLabel.setFont(Font.font(FUENTE, FontWeight.EXTRA_BOLD, 30));
        nombreLabel.setAlignment(Pos.CENTER);
        nombreLabel.setTextFill(Color.BLACK);
        nombreLabel.setWrapText(true);
        nombreLabel.setMaxWidth(220);

        labelsBox.getChildren().add(nombreLabel);

        // Descripción
        if (carta instanceof Especial) {
            Especial especial = (Especial) carta;
            Label descripcionLabel = new Label(especial.obtenerDescripcion());
            descripcionLabel.setFont(Font.font(FUENTE, FontWeight.EXTRA_BOLD, 18));
            descripcionLabel.setAlignment(Pos.CENTER);
            descripcionLabel.setTextFill(Color.BLACK);
            descripcionLabel.setWrapText(true);
            descripcionLabel.setMaxWidth(220);

            labelsBox.getChildren().add(descripcionLabel);
        }

        infoPane.getChildren().add(labelsBox);
        getChildren().add(infoPane);
    }
}
