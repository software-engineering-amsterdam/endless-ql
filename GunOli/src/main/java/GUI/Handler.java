package GUI;

import java.awt.event.ActionEvent;
import java.io.File;

import ParseObjects.Form;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Application.Parser;

public class Handler implements EventHandler<javafx.event.ActionEvent> {
    private final FileChooser fileChooser = new FileChooser();
    public Handler(Stage stage){

    }


    public Button createFileBrowseButton(){
        Button button = new Button();
        button.setText("Browse");
        button.setDefaultButton(true);
        button.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            public void handle(javafx.event.ActionEvent event) {

            }
        });
        return button;
    }

    public Button createDebugButton(){
        Button button = new Button();
        button.setText("Debug");
        button.setDefaultButton(true);
        return button;
    }

    public void init(Stage stage){
        stage.setTitle("Endless-ql");

        Button fileBrowseBtn = createFileBrowseButton();
        Button debugBtn = createDebugButton();



        StackPane layout = new StackPane();
        layout.getChildren().add(fileBrowseBtn);

        Scene scene = new Scene(layout, 300, 300);
        stage.setScene(scene);
        stage.show();

    }


    public void handle(javafx.event.ActionEvent event){
        File file = fileChooser.showOpenDialog(stage);
        if(!file.equals(null)){
            Parser parser = new Parser();
            Form form = parser.parseInputToForm(file.getPath());
            parser.printQLForm(form);

            if(form.equals(null)) {
                Platform.exit();
            }

        }
    }


}
