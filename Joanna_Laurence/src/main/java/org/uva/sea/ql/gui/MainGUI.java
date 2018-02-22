package org.uva.sea.ql.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/form.fxml"));
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setTitle("Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
