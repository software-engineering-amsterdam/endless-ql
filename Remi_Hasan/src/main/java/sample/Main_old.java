package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main_old extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
//        Parent root = FXMLLoader.load(classLoader.getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        new ArrayList<Integer>().stream().map(x -> {
            return x;
        }).collect(Collectors.toCollection(ArrayList::new));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
