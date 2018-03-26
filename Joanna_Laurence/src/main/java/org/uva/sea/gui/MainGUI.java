package org.uva.sea.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

class MainGUI extends Application {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final String GUI_FORM_FXML = "/gui/form.fxml";

    public static void main(final String[] args) {
        Application.launch(args);
    }

    @Override
    public final void start(final Stage primaryStage) {
        try {
            final Parent window = FXMLLoader.load(this.getClass().getResource(MainGUI.GUI_FORM_FXML));
            final Scene scene = new Scene(window, MainGUI.WIDTH, MainGUI.HEIGHT);
            primaryStage.setTitle("Form");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
