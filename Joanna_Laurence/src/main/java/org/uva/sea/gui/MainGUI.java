package org.uva.sea.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGUI extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent window = FXMLLoader.load(this.getClass().getResource("/gui/form.fxml"));
            Scene scene = new Scene(window, MainGUI.WIDTH, MainGUI.HEIGHT);
            primaryStage.setTitle("Form");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            //TODO: Display error message in gui
            e.printStackTrace();
        }
    }
}
