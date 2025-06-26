package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Componente visual para representar una carta en la interfaz
 */
public class CartaView extends VBox {

    private Carta carta;
    private static final double ancho = 60;
    private static final double alto = 90;
    private Boolean animacionElevar;

    public CartaView(Carta carta, GwentApp app, Boolean animacionElevar) {
        this.carta = carta;
        this.animacionElevar = animacionElevar;

        setSpacing(5);

        setMinSize(ancho, alto);
        setPrefSize(ancho, alto);
        setMaxSize(ancho, alto);

        String nombreCarta = obtenerNombreCarta(carta);

        // Fondo de la carta
        String nombreCartaImagen = nombreCarta.toLowerCase().replace(" ", "_") + ".jpg";
        try {
            //setBackground(new Background(app.obtenerBackgroundImage("/imagenes/cartas/place_holder.jpg")));
            setBackground(new Background(app.obtenerBackgroundImage("/imagenes/cartas/" + nombreCartaImagen)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Espaciador para empujar el resto hacia abajo
        Region espacio = new Region();
        VBox.setVgrow(espacio, Priority.ALWAYS);


        // Valor de puntaje (solo para unidades)
        if (carta instanceof Unidad) {
            Unidad unidad = (Unidad) carta;
            String nombreModificador = unidad.obtenerModificador().getClass().getSimpleName();
            int puntaje = unidad.getPuntaje().obtenerValor();

            // Mostramos puntaje
            Label puntajeLabel = new Label(String.valueOf(puntaje));
            puntajeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 8));
            puntajeLabel.setTranslateX(-5.5);
            puntajeLabel.setTranslateY(-5.5);

            StackPane puntajePane;

            try {
                if (nombreModificador.equals("Legendaria")) {
                    puntajePane = app.crearIcono("/imagenes/iconos/puntaje_legendaria.png", 30.0);
                    puntajeLabel.setStyle("-fx-text-fill: #FFF;");
                } else {
                    puntajePane = app.crearIcono("/imagenes/iconos/puntaje_normal.png", 30.0);
                    puntajeLabel.setStyle("-fx-text-fill: #000;");
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            StackPane.setAlignment(puntajeLabel, Pos.CENTER);
            puntajePane.setTranslateX(-4.5);
            puntajePane.setTranslateY(-4.5);
            puntajePane.getChildren().add(puntajeLabel);

            getChildren().addAll(puntajePane, espacio);

            // Parte inferior de la carta
            HBox inferiorBox = new HBox();

            // Espaciador para empujar a la derecha
            Region espacioInferior = new Region();
            HBox.setHgrow(espacioInferior, Priority.ALWAYS);
            inferiorBox.getChildren().add(espacioInferior);

            // Mostramos el modificador
            if (!nombreModificador.equals("SinModificador") && !nombreModificador.equals("Legendaria")) {
                String nombreModificadorImagen = nombreModificador.toLowerCase() + "_modificador.png";
                StackPane modificadorPane;
                try {
                    modificadorPane = app.crearIcono("/imagenes/iconos/" + nombreModificadorImagen, 15.0);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                inferiorBox.getChildren().add(modificadorPane);

            }

            // Mostramos seccion
            List<Seccion> secciones = unidad.obtenerSecciones();
            String nombreImagen;
            // TODO aca hay un problema con los agiles: al jugar una carta agil, parece vaciar su lista de secciones
            if (secciones.size() != 1) {
                nombreImagen = "cuerpoacuerpo_rango_seccion.png";
            } else {
                nombreImagen = unidad.obtenerSecciones().get(0).getClass().getSimpleName().toLowerCase() + "_seccion.png";
            }
            StackPane seccionPane;
            try {
                seccionPane = app.crearIcono("/imagenes/iconos/" + nombreImagen, 15.0);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            inferiorBox.getChildren().add(seccionPane);

            getChildren().add(inferiorBox);

        } else if (carta instanceof Especial) {
            Especial especial = (Especial) carta;

            String nombreImagen = especial.getNombre().toLowerCase().replace(" ", "_") + "_efecto.png";
            StackPane especialPane;
            try {
                especialPane = app.crearIcono("/imagenes/iconos/" + nombreImagen, 15.0);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            getChildren().addAll(especialPane, espacio);
        }
        // Configurar estilo del contenedor
        setStyle("-fx-border-color: black; -fx-border-width: 1;");

        // Efectos
        var scaleTrans = new ScaleTransition(Duration.millis(250), this);
        scaleTrans.setFromX(1.0);
        scaleTrans.setFromY(1.0);
        scaleTrans.setToX(2.0);
        scaleTrans.setToY(2.0);

        var translateTrans = new TranslateTransition(Duration.millis(250), this);
        translateTrans.setFromY(0);
        translateTrans.setToY(-100);

        setOnMouseEntered(e -> {
            scaleTrans.setRate(1.0);
            setViewOrder(-1.0);
            scaleTrans.play();

            if (animacionElevar) {
                translateTrans.setRate(1.0);
                translateTrans.play();
            }
        });
        setOnMouseExited(e -> {
            scaleTrans.setRate(-1.0);
            setViewOrder(0.0);
            scaleTrans.play();

            if (animacionElevar) {
                translateTrans.setRate(-1.0);
                translateTrans.play();
            }
        });

    }

    public static double getAncho() {
        return ancho;
    }

    public static double getAlto() {
        return alto;
    }

    /**
     * Intenta obtener un nombre más amigable de la carta
     */
    private String obtenerNombreCarta(Carta carta) {
        // Usar instanceof para determinar el tipo de carta
        if (carta instanceof Unidad) {
            Unidad unidad = (Unidad) carta;
            try {
                Method metodo = unidad.getClass().getMethod("getNombre");
                return (String) metodo.invoke(unidad);
            } catch (Exception e) {
                // Si no tiene el método, mostrar el nombre de la clase
                return "Unidad";
            }
        } else if (carta instanceof Especial) {
            try {
                Method metodo = carta.getClass().getMethod("getNombre");
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
