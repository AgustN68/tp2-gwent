package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Componente visual para representar una carta en la interfaz
 */
public class CartaView extends VBox {

    private Carta carta;

    public CartaView(Carta carta) {
        this.carta = carta;

        setPadding(new Insets(5));
        setSpacing(5);
        setAlignment(Pos.CENTER);
        setPrefSize(100, 140);

        // Fondo de la carta
        Rectangle fondo = new Rectangle(90, 130);
        fondo.setFill(Color.LIGHTGRAY);
        fondo.setStroke(Color.BLACK);
        fondo.setArcWidth(10);
        fondo.setArcHeight(10);

        // Nombre de la carta - intentamos obtener un nombre más amigable
        String nombreCarta = obtenerNombreCarta(carta);
        Label nombreLabel = new Label(nombreCarta);
        nombreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        nombreLabel.setWrapText(true);
        nombreLabel.setMaxWidth(80);

        // Valor de puntaje (solo para unidades)
        if (carta instanceof Unidad) {
            Unidad unidad = (Unidad) carta;
            int puntaje = unidad.getPuntaje().obtenerValor();
            Label puntajeLabel = new Label("Puntos: " + puntaje);
            puntajeLabel.setFont(Font.font("Arial", 11));
            getChildren().addAll(fondo, nombreLabel, puntajeLabel);
        } else {
            // Si no es unidad, solo mostrar el nombre
            getChildren().addAll(fondo, nombreLabel);
        }

        // Configurar estilo visual del contenedor
        setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: #f0f0f0;");
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
