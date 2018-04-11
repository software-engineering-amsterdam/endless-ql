package GUI;
import QL.AST.Form;
import QL.Analysis.TypeChecker;
import QL.Evaluation.ExpressionTable;
import QLS.ParseObjectQLS.Stylesheet;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

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
            ExpressionTable expressionTable = new ExpressionTable(form);

            performAnalysis(form, expressionTable);

            //todo: static analysis typechecker
            Stylesheet stylesheet = parser.parseInputToStyleSheet(styleSheetFile.getPath());

            //parser.printQLForm(form); //debug print the form questions in console
            //parser.printQLSStyleSheet(stylesheet); //debug partially print stylesheet to console

            FormBuilder formBuilder = new FormBuilder(form, expressionTable, stage);
            formBuilder.renderForm();
        });

        layout.getChildren().add(debugBtn);
    }

    public void performAnalysis(Form form, ExpressionTable expressionTable){
        TypeChecker typechecker = new TypeChecker(form, expressionTable);
        typechecker.detectCyclicDependencies();
        typechecker.typeCheck();
        typechecker.detectLabelDuplication();
    }

    private void createBrowseButton(Stage stage, HBox layout){
        Button browseBtn = new Button("Browse");

        browseBtn.setOnAction((event) ->{
            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                Parser parser = new Parser();
                Form form = parser.parseInputToForm(file.getPath());
                ExpressionTable expressionTable = new ExpressionTable(form);
                //performAnalysis(form, expressionTable);
                //parser.printQLForm(form);
                if (form == null) { Platform.exit(); }
                else {
                    FormBuilder formBuilder = new FormBuilder(form, expressionTable, stage);
                    formBuilder.renderForm();
                    performAnalysis(form, expressionTable);
                }
            }
        });

        layout.getChildren().add(browseBtn);
    }
}
