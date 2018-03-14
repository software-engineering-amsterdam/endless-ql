package GUI;
import java.io.File;

import QL.ParseObjectsQL.Form;
import QL.ParseObjectsQL.QuestionMap;
import QLS.ParseObjectQLS.Stylesheet;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Application.Parser;

public class QLUserInterface {
    public QLUserInterface(Stage stage){
        HBox hBox = new HBox(5);
        createBrowseButton(stage, hBox);
        createDebugButton(stage, hBox);
        hBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(hBox, 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void createDebugButton(Stage stage, HBox layout){
        Button debugBtn = new Button("Debug");

        debugBtn.setOnAction((event) ->{
            String formPath = "./src/main/resources/example.ql";
            String styleSheetPath = "./src/main/resources/example.qls";

            File formFile = new File(formPath);
            File styleSheetFile = new File(styleSheetPath);

            Parser parser = new Parser();

            Form form = parser.parseInputToForm(formFile.getPath());

            Stylesheet stylesheet = parser.parseInputToStyleSheet(styleSheetFile.getPath());

            parser.printQLForm(form); //debug print the form questions in console
            //parser.printQLSStyleSheet(stylesheet); //debug partially print stylesheet to console

            FormBuilder formBuilder = new FormBuilder(form, stage);
            formBuilder.renderForm();
        });

        layout.getChildren().add(debugBtn);
    }

    private void createBrowseButton(Stage stage, HBox layout){
        Button browseBtn = new Button("Browse");

        browseBtn.setOnAction((event) ->{
            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                Parser parser = new Parser();
                Form form = parser.parseInputToForm(file.getPath());
                QuestionMap questionMap = new QuestionMap(form);
                if (form == null) { Platform.exit(); }
                else {
                    FormBuilder formBuilder = new FormBuilder(form, stage);
                    formBuilder.renderForm();
                }
            }
        });

        layout.getChildren().add(browseBtn);
    }
}
