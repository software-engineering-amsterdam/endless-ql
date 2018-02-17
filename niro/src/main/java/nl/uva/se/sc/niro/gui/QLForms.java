package nl.uva.se.sc.niro.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class QLForms extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/nl/uva/se/sc/niro/gui/QLHome.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("QL Forms");
        stage.setScene(scene);
        stage.show();
    }
}
