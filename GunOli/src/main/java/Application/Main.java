package Application;

import GUI.QLUserInterface;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;


public class Main extends Application{


    @Override
    public void start(Stage stage){
       //BuildGUI; <- 2 buttons (browse, debug)
            //Browse <- returns form from file
            //Debug  <- example.ql

       //Parse selected form
        // Build form object
        // display questions in GUI <- implement submit button
        // handle user input

        stage.setTitle("QL displayer");
        QLUserInterface qlGui = new QLUserInterface(stage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}