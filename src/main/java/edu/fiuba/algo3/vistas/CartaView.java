package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

/**
 * Componente visual para representar una carta en la interfaz
 */
public class CartaView extends VBox {

    private Carta carta;

    public CartaView(Carta carta) {
        this.carta = carta;
        double ancho = 50;
        double alto = 70;

        // para evitar problemas (como desplazamientos) al incrementar el tamaño
        setPadding(new Insets(5));
        setSpacing(5);
        setAlignment(Pos.TOP_CENTER);

        setMinSize(ancho, alto);
        setPrefSize(ancho, alto);
        setMaxSize(ancho, alto);

        // Nombre de la carta
        String nombreCarta = obtenerNombreCarta(carta);
        Label nombreLabel = new Label(nombreCarta);
        nombreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 8));
        nombreLabel.setWrapText(true);

        getChildren().add(nombreLabel);

        // Fondo de la carta
        Image fondoImage;
        if (getClass().getResourceAsStream("/imagenes/cartas/" + nombreCarta + ".png") == null) {
            fondoImage = new Image(getClass().getResourceAsStream("/imagenes/cartas/placeholder.png"));
        } else {
            fondoImage = new Image(getClass().getResourceAsStream("/imagenes/cartas/" + nombreCarta + ".png"));
        }
        BackgroundImage fondo = new BackgroundImage(
                fondoImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,true,true,false, true)
        );
        setBackground(new Background(fondo));


        // Valor de puntaje (solo para unidades)
        if (carta instanceof Unidad) {
            Unidad unidad = (Unidad) carta;
            int puntaje = unidad.getPuntaje().obtenerValor();
            Label puntajeLabel = new Label("Puntos: " + puntaje);
            puntajeLabel.setFont(Font.font("Arial", 8));

            // Espaciador para empujar el puntaje hacia abajo
            Region espacio = new Region();
            VBox.setVgrow(espacio, Priority.ALWAYS);

            getChildren().addAll(espacio, puntajeLabel);
        }
        // Configurar estilo del contenedor
        setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: #f0f0f0;");

        // Efectos
        var scaleTrans = new ScaleTransition(Duration.millis(250), this);
        scaleTrans.setFromX(1.0);
        scaleTrans.setFromY(1.0);
        scaleTrans.setToX(2.0);
        scaleTrans.setToY(2.0);

        setOnMouseEntered(e -> {
            scaleTrans.setRate(1.0);
            setViewOrder(-1.0);
            scaleTrans.play();
        });
        setOnMouseExited(e -> {
            scaleTrans.setRate(-1.0);
            setViewOrder(0.0);
            scaleTrans.play();
        });

    }

    void establecerEstilo(String s) {
        setStyle(s);
    }

    /**
     * Intenta obtener un nombre más amigable de la carta
     */
    private String obtenerNombreCarta(Carta carta) {
        // Usar instanceof para determinar el tipo de carta
        if (carta instanceof edu.fiuba.algo3.modelo.Carta.Unidad) {
            edu.fiuba.algo3.modelo.Carta.Unidad unidad = (edu.fiuba.algo3.modelo.Carta.Unidad) carta;
            try {
                java.lang.reflect.Method metodo = unidad.getClass().getMethod("getNombre");
                return (String) metodo.invoke(unidad);
            } catch (Exception e) {
                // Si no tiene el método, mostrar el nombre de la clase
                return "Unidad";
            }
        } else if (carta instanceof edu.fiuba.algo3.modelo.Carta.Especial.Especial) {
            try {
                java.lang.reflect.Method metodo = carta.getClass().getMethod("getNombre");
                return (String) metodo.invoke(carta);
            } catch (Exception e) {
                // Si no tiene el método, mostrar el tipo de carta especial
                return carta.getClass().getSimpleName();
            }
        } else {
            // Si es otro tipo de carta, mostrar el nombre de la clase
            return carta.getClass().getSimpleName();
        }
    }

    public Carta getCarta() {
        return carta;
    }
}
