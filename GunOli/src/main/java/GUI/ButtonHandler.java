package GUI;

//import java.awt.event.ActionEvent;
import java.io.File;

import ParseObjects.Form;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Application.Parser;


public class ButtonHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event){
        System.out.println("Hello I am here");
        if(event.getSource().toString().equals("fileBrowseBtn")){
            System.out.println("Hello");
        }
    }

}
