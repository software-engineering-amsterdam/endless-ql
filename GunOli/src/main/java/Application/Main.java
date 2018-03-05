package Application;

import GUI.Handler;
import com.sun.corba.se.pept.transport.EventHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;


import ParseObjects.Question;
import ParseObjects.Condition;
import ParseObjects.Form;


public class Main extends Application{


    @Override
    public void start(Stage stage){
        Handler handler = new Handler(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}