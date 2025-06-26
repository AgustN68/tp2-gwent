package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Especial.Especial;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Puntaje;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Componente visual para representar una carta en la interfaz
 */
public class CartaView extends VBox {

    private static final double ANCHO = 60;
    private static final double ALTO = 90;
    private static final String FUENTE = "serif";

    private Carta carta;
    private Boolean animacionElevar;

    // Control de carta levantada (solo una carta puede estar levantada a la vez)
    private static CartaView cartaLevantada = null;
    // Control de carta seleccionada (solo una carta puede estar seleccionada a la vez)
    private static CartaView cartaSeleccionada = null;

    public CartaView(Carta carta, GwentApp app, Boolean animacionElevar) {
        this.carta = carta;
        this.animacionElevar = animacionElevar;

        setSpacing(5);
        setMinSize(ANCHO, ALTO);
        setPrefSize(ANCHO, ALTO);
        setMaxSize(ANCHO, ALTO);

        setAlignment(Pos.TOP_LEFT);

        String nombreCarta = obtenerNombreCarta(carta);
        setFondoCarta(app, nombreCarta);

        Region espacio = new Region();
        VBox.setVgrow(espacio, Priority.ALWAYS);

        if (carta instanceof Unidad) {
            getChildren().addAll(crearPuntajePane(app, (Unidad) carta), espacio, crearInferiorBox(app, (Unidad) carta));
        } else if (carta instanceof Especial) {
            getChildren().addAll(crearEspecialPane(app, (Especial) carta), espacio);
        }

        setStyle("-fx-border-color: black; -fx-border-width: 1;");

        configurarAnimaciones();
    }

    private void setFondoCarta(GwentApp app, String nombreCarta) {
        String nombreCartaImagen = nombreCarta.toLowerCase().replace(" ", "_") + ".jpg";
        try {
            setBackground(new Background(app.obtenerBackgroundImage("/imagenes/cartas/" + nombreCartaImagen)));
        } catch (FileNotFoundException e) {
            setBackground(null);
        }
    }

    private StackPane crearPuntajePane(GwentApp app, Unidad unidad) {
        String nombreModificador = unidad.obtenerModificador().getClass().getSimpleName();
        int puntaje = unidad.getPuntaje().obtenerValor();

        Label puntajeLabel = new Label(String.valueOf(puntaje));
        puntajeLabel.setFont(Font.font(FUENTE, FontWeight.EXTRA_BOLD, 9));
        puntajeLabel.setAlignment(Pos.CENTER);

        puntajeLabel.setTranslateX(-5.5);
        puntajeLabel.setTranslateY(-5.5);

        StackPane puntajePane;
        try {
            if (nombreModificador.equals("Legendaria")) {
                puntajePane = app.crearIcono("/imagenes/iconos/puntaje_legendaria.png", 30.0);
                puntajeLabel.setStyle("-fx-text-fill: #fffbe6;");
                puntajeLabel.setEffect(new DropShadow(2, Color.BLACK));
            } else {
                puntajePane = app.crearIcono("/imagenes/iconos/puntaje_normal.png", 30.0);
                puntajeLabel.setStyle("-fx-text-fill: #000000;");
                puntajeLabel.setEffect(new DropShadow(2, Color.WHITE));
            }
        } catch (FileNotFoundException e) {
            puntajePane = new StackPane();
        }
        StackPane.setAlignment(puntajeLabel, Pos.CENTER);
        puntajePane.setTranslateX(-2.5);
        puntajePane.setTranslateY(-2.5);
        puntajePane.getChildren().add(puntajeLabel);
        return puntajePane;
    }

    private HBox crearInferiorBox(GwentApp app, Unidad unidad) {
        HBox inferiorBox = new HBox();
        inferiorBox.setAlignment(Pos.CENTER_RIGHT);

        Region espacioInferior = new Region();
        HBox.setHgrow(espacioInferior, Priority.ALWAYS);
        inferiorBox.getChildren().add(espacioInferior);

        // Modificador
        String nombreModificador = unidad.obtenerModificador().getClass().getSimpleName();
        if (!nombreModificador.equals("SinModificador") && !nombreModificador.equals("Legendaria")) {
            String nombreModificadorImagen = nombreModificador.toLowerCase() + "_modificador.png";
            try {
                inferiorBox.getChildren().add(app.crearIcono("/imagenes/iconos/" + nombreModificadorImagen, 15.0));
            } catch (FileNotFoundException ignored) {}
        }

        // Sección
        List<Seccion> secciones = unidad.obtenerSecciones();
        String nombreImagen;
        if (secciones.size() != 1) {
            nombreImagen = "cuerpoacuerpo_rango_seccion.png";
        } else {
            nombreImagen = unidad.obtenerSecciones().get(0).getClass().getSimpleName().toLowerCase() + "_seccion.png";
        }
        try {
            inferiorBox.getChildren().add(app.crearIcono("/imagenes/iconos/" + nombreImagen, 15.0));
        } catch (FileNotFoundException ignored) {}

        return inferiorBox;
    }

    private StackPane crearEspecialPane(GwentApp app, Especial especial) {
        String nombreImagen = especial.getNombre().toLowerCase().replace(" ", "_") + "_efecto.png";
        try {
            return app.crearIcono("/imagenes/iconos/" + nombreImagen, 15.0);
        } catch (FileNotFoundException e) {
            return new StackPane();
        }
    }

    private void configurarAnimaciones() {
        var scaleTrans = new ScaleTransition(Duration.millis(250), this);
        scaleTrans.setFromX(1.0);
        scaleTrans.setFromY(1.0);
        scaleTrans.setToX(2.0);
        scaleTrans.setToY(2.0);

        var translateTrans = new TranslateTransition(Duration.millis(250), this);
        translateTrans.setFromY(0);
        translateTrans.setToY(-50);

        setOnMouseEntered(e -> {
            if (cartaSeleccionada == null && (cartaLevantada == null || cartaLevantada == this)) {
                cartaLevantada = this;
                scaleTrans.setRate(1.0);
                setViewOrder(-1.0);
                scaleTrans.play();

                if (animacionElevar) {
                    translateTrans.setRate(1.0);
                    translateTrans.play();
                }
            }
        });
        setOnMouseExited(e -> {
            if (cartaSeleccionada == null && cartaLevantada == this) {
                cartaLevantada = null;
                scaleTrans.setRate(-1.0);
                setViewOrder(0.0);
                scaleTrans.play();

                if (animacionElevar) {
                    translateTrans.setRate(-1.0);
                    translateTrans.play();
                }
            }
        });
    }

    public static double getAncho() {
        return ANCHO;
    }

    public static double getAlto() {
        return ALTO;
    }

    public static void resetCartaSeleccionada() {
        cartaLevantada = null;
        cartaSeleccionada = null;
    }

    /**
     * Intenta obtener un nombre más amigable de la carta
     */
    private String obtenerNombreCarta(Carta carta) {
        if (carta instanceof Unidad) {
            Unidad unidad = (Unidad) carta;
            try {
                Method metodo = unidad.getClass().getMethod("getNombre");
                return (String) metodo.invoke(unidad);
            } catch (Exception e) {
                return "Unidad";
            }
        } else if (carta instanceof Especial) {
            try {
                Method metodo = carta.getClass().getMethod("getNombre");
                return (String) metodo.invoke(carta);
            } catch (Exception e) {
                return carta.getClass().getSimpleName();
            }
        } else {
            return carta.getClass().getSimpleName();
        }
    }

    public Carta getCarta() {
        return carta;
    }

    public void seleccionarCarta() {
        if (cartaLevantada == this) {
            cartaSeleccionada = this;
            setStyle("-fx-border-color: gold; -fx-border-width: 1;");
        }
    }

    public static CartaView getCartaSeleccionada() {
        return cartaSeleccionada;
    }

    public void deseleccionarCarta() {
        if (cartaSeleccionada == this) {
            cartaSeleccionada = null;
            setStyle("-fx-border-color: black; -fx-border-width: 1;");
        }
    }
}
