package Application;

import GUI.QLUserInterface;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;


public class Main extends Application{


    @Override
    public void start(Stage stage){
        stage.setTitle("QL displayer");
        QLUserInterface qlGui = new QLUserInterface(stage);
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}