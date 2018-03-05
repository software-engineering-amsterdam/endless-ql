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

import javafx.event.EventHandler;

public class QLUserInterface {

    private Stage stage;


    public QLUserInterface(Stage InboundStage){
        this.stage = InboundStage;

        //Button fileBrowseBtn = createBrowseButton(stage);
        Button fileBrowseBtn = new Button("Browse");

        fileBrowseBtn.setOnAction((event) ->{
            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            if (!file.equals(null)) {
                Parser parser = new Parser();
                Form form = parser.parseInputToForm(file.getPath());
                parser.printQLForm(form);

                if (form.equals(null)) {
                    Platform.exit();
                }

            }

        });




        StackPane layout = new StackPane();
        layout.getChildren().add(fileBrowseBtn);
        Scene scene = new Scene(layout, 300, 300);
        stage.setScene(scene);
        stage.show();


    }

}
