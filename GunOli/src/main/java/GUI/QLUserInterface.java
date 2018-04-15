package GUI;

import QL.AST.Form;
import QL.Analysis.QLTypeChecker;
import QL.Evaluation.ExpressionTable;
import QLS.AST.Stylesheet;
import QLS.Analysis.QLSTypeChecker;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class QLUserInterface {
    private Form form;
    private Stylesheet stylesheet;

    public QLUserInterface(Stage stage){
        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER);

        form = null;
        stylesheet = null;

        //create Buttons
        createBrowseButton(stage, vBox, "QL");
        createBrowseButton(stage, vBox, "QLS");
        createDebugButton(stage, vBox);
        createRenderButton(stage, vBox);

        //build scene
        Scene scene = new Scene(vBox, 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void createDebugButton(Stage stage, VBox layout){
        Button debugBtn = new Button("Debug");
        HBox buttonContainer = new HBox(5);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.getChildren().add(debugBtn);

        debugBtn.setOnAction((event) ->{
            String formPath = "./src/main/resources/test.ql";
            String styleSheetPath = "./src/main/resources/test.qls";

            File formFile = new File(formPath);
            File styleSheetFile = new File(styleSheetPath);

            Text debugQL = new Text(formFile.getName());
            Text debugQLS = new Text(styleSheetFile.getName());

            buttonContainer.getChildren().add(debugQL);
            buttonContainer.getChildren().add(debugQLS);

            Parser parser = new Parser();

            form = parser.parseInputToForm(formFile.getPath());
            stylesheet = parser.parseInputToStyleSheet(styleSheetFile.getPath());
        });

        layout.getChildren().add(buttonContainer);
    }

    private void createBrowseButton(Stage stage, VBox layout, String buttonName){
        HBox buttonContainer = new HBox(5);
        buttonContainer.setAlignment(Pos.CENTER);
        Button browseBtn = new Button(buttonName);
        buttonContainer.getChildren().add(browseBtn);

        browseBtn.setOnAction((event) ->{
            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                Text fileText = new Text(file.getName());
                buttonContainer.getChildren().add(fileText);

                Parser parser = new Parser();

                if(buttonName.equals("QL")) {
                    form = parser.parseInputToForm(file.getPath());
                } else {
                    stylesheet = parser.parseInputToStyleSheet(file.getPath());
                }
            }
        });

        layout.getChildren().addAll(buttonContainer);
    }

    private void createRenderButton(Stage stage, VBox layout){
        Button renderBtn = new Button("Render");

        renderBtn.setOnAction((event) ->{
            ExpressionTable expressionTable = new ExpressionTable(form);
            performAnalysis(form, expressionTable, stylesheet);
            FormBuilder formBuilder = new FormBuilder(form, expressionTable, stage);
            formBuilder.renderForm();
        });

        layout.getChildren().addAll(renderBtn);
    }

    private void performAnalysis(Form form, ExpressionTable expressionTable, Stylesheet stylesheet){
        QLTypeChecker qlTypeChecker = new QLTypeChecker(form, expressionTable);
        qlTypeChecker.detectCyclicDependencies();
        qlTypeChecker.typeCheck();
        qlTypeChecker.detectLabelDuplication();

        if(stylesheet != null) {
            QLSTypeChecker qlsTypeChecker = new QLSTypeChecker(stylesheet, form);
            qlsTypeChecker.typeCheck();
            qlsTypeChecker.detectUnknownReferences();
            qlsTypeChecker.detectDuplicateQuestions();
            qlsTypeChecker.detectUnplacedQuestions();
        }
    }
}
