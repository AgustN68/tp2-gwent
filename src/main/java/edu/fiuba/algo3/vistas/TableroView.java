package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.GwentController;
import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.Unidad;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Seccion.Seccion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;
import java.util.List;

public class TableroView extends BorderPane {

    private GwentController controller;
    private GwentApp app;
    VBox tableroBox;

    public TableroView(GwentController controller, GwentApp app) {
        this.controller = controller;
        this.app = app;

        setPadding(new Insets(10));

        // Lado izquierdo
        configurarIzquierda();

        // Centro - tablero de juego
        configurarTablero();

        // Parte inferior - mano del jugador actual y botones de acción
        configurarManoYAcciones();
    }

    private void configurarIzquierda() {
        // Informacion
        VBox informacionBox = new VBox(10);
        informacionBox.setAlignment(Pos.CENTER);



        // Botones de acción
        Button pasarButton = new Button("Pasar");
        pasarButton.setFont(Font.font("Arial", 16));
        pasarButton.setPrefSize(150, 50);
        pasarButton.setOnAction(e -> {
            controller.pasarTurno();
            actualizarVista();
        });

        JugadorView jugador1View = new JugadorView(app, controller.getJugador1(), 1);
        JugadorView jugador2View = new JugadorView(app, controller.getJugador2(), 2);

        // Mostrar el turno actual
        jugador1View.actualizarTurno(controller.getJugadorActual().getNombre());
        jugador2View.actualizarTurno(controller.getJugadorActual().getNombre());

        informacionBox.getChildren().addAll(
                jugador2View,
                jugador1View,
                pasarButton
        );

        // Crear un contenedor HBox para agregar margen a la derecha
        HBox izquierdaBox = new HBox();

        // Espaciador para margen
        Region espaciador = new Region();
        espaciador.setMinWidth(10); // Margen entre lado izquierdo y el tablero

        izquierdaBox.getChildren().addAll(informacionBox, espaciador);
        setLeft(izquierdaBox);
    }

    private void configurarTablero() {
        // Contenedor principal para el tablero
        tableroBox = new VBox(2);
        tableroBox.setAlignment(Pos.CENTER);
        tableroBox.setPadding(new Insets(0));

        Jugador jugador1 = controller.getJugador1();
        Jugador jugador2 = controller.getJugador2();

        // Crear las vistas para cada sección del tablero
        List<Seccion> seccionesJ1 = jugador1.getTablero().getSecciones();
        List<Seccion> seccionesJ2 = jugador2.getTablero().getSecciones();

        // Asegurarnos de que tenemos todas las secciones necesarias y las añadimos en el orden correcto
        if (seccionesJ1.size() >= 3 && seccionesJ2.size() >= 3) {
            // Añadir las secciones al tablero en el orden requerido
            tableroBox.getChildren().addAll(
                    new SeccionView(seccionesJ2.get(2), "Asedio", jugador2.getNombre(), app, 2),
                    new SeccionView(seccionesJ2.get(1), "Rango", jugador2.getNombre(), app, 2),
                    new SeccionView(seccionesJ2.get(0), "CuerpoACuerpo", jugador2.getNombre(), app, 2),
                    new SeccionView(seccionesJ1.get(0), "CuerpoACuerpo", jugador1.getNombre(), app, 1),
                    new SeccionView(seccionesJ1.get(1), "Rango", jugador1.getNombre(), app, 1),
                    new SeccionView(seccionesJ1.get(2), "Asedio", jugador1.getNombre(), app, 1)
            );
        }

        for (Node seccionView : tableroBox.getChildren()) {
            SeccionView view = (SeccionView) seccionView;
            view.setOnMouseClicked(e -> {
                if (view.esJugable()) {
                    jugarCarta(CartaView.getCartaSeleccionada(), view.obtenerPosicionCartaSeleccionada());
                }
            });
        }

        // Backgound del tablero

        try {
            setBackground(new Background(app.obtenerBackgroundImage("/imagenes/fondos/tablero.jpg")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        setCenter(tableroBox);
    }

    private void jugarCarta(CartaView cartaView, int posicion) {
        cartaView.setDisable(true); // Evita clicks múltiples
        controller.jugarCarta(posicion);
        actualizarVista();
    }

    private void configurarManoYAcciones() {
        HBox contenedorInferior = new HBox(5);
        contenedorInferior.setAlignment(Pos.CENTER_LEFT);
        contenedorInferior.setPadding(new Insets(5));

        // Mostrar las cartas en la mano del jugador actual
        HBox manoBox = new HBox(2);
        manoBox.setAlignment(Pos.BOTTOM_CENTER);
        manoBox.setPadding(new Insets(5));
        manoBox.setMinHeight(CartaView.getAlto()+80);
        HBox.setHgrow(manoBox, Priority.ALWAYS);

        Jugador jugadorActual = controller.getJugadorActual();
        List<Carta> cartasEnMano = jugadorActual.verMano();
        for (int i = 0; i < cartasEnMano.size(); i++) {
            Carta carta = cartasEnMano.get(i);
            final int posicion = i;

            CartaView cartaView = new CartaView(carta, app, true);
            cartaView.setOnMouseClicked(e -> {

                // Solo permitir click si no hay carta levantada/seleccionada
                CartaView cartaSeleccionada = CartaView.getCartaSeleccionada();
                if (cartaSeleccionada == null) {
                    if (carta instanceof Unidad) {
                        seleccionarUnidad(posicion, jugadorActual, cartaView);
                    } else {
                        jugarCarta(cartaView, posicion);
                    }
                } else if (cartaView.equals(cartaSeleccionada)) {
                    cartaView.deseleccionarCarta();
                    for (Node seccionView : tableroBox.getChildren()) {
                        ((SeccionView) seccionView).seccionDeseleccionar();
                    }
                }
            });

            manoBox.getChildren().add(cartaView);
        }

        contenedorInferior.getChildren().add(manoBox);

        setBottom(contenedorInferior);
    }

    private void seleccionarUnidad(int posicion, Jugador jugadorActual, CartaView cartaViewUnidad) {
        cartaViewUnidad.seleccionarCarta();
        List<Seccion> secciones = ((Unidad)cartaViewUnidad.getCarta()).obtenerSecciones();
        for (Seccion seccion : secciones) {
            for (Node seccionView : tableroBox.getChildren()) {
                ((SeccionView) seccionView).seccionSeleccionada(posicion, jugadorActual.getNombre(), seccion.getClass().getSimpleName());
            }
        }
    }

    // Actualizar la vista cuando cambie el estado del juego
    public void actualizarVista() {
        CartaView.resetCartaSeleccionada();

        // Eliminar todos los nodos actuales
        getChildren().clear();

        // Volver a configurar la vista
        configurarIzquierda();
        configurarTablero();
        configurarManoYAcciones();
    }
}
