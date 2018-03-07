package GUI;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;

import ParseObjects.Form;
import ParseObjects.QuestionMap;
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
    public QLUserInterface(Stage stage){
        VBox vBox = new VBox(5);
        createBrowseButton(stage, vBox);
        createDebugButton(stage, vBox);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void createDebugButton(Stage stage, VBox layout){
        Button debugBtn = new Button("Debug");

        debugBtn.setOnAction((event) ->{
            String filePath = "./src/main/resources/example.ql";
            File file = new File(filePath);
            Parser parser = new Parser();
            Form form = parser.parseInputToForm(file.getPath());
            QuestionMap questionMap = new QuestionMap(form);
            parser.printQLForm(form, questionMap); //debug print the form questions in console
        });

        layout.getChildren().add(debugBtn);
    }

    private void createBrowseButton(Stage stage, VBox layout){
        Button browseBtn = new Button("Browse");

        browseBtn.setOnAction((event) ->{
            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                Parser parser = new Parser();
                Form form = parser.parseInputToForm(file.getPath());
                if (form == null) { Platform.exit(); }
                else {
                    FormBuilder formBuilder = new FormBuilder();
                    formBuilder.renderForm(form);
                }
            }
        });

        layout.getChildren().add(browseBtn);
    }
}
