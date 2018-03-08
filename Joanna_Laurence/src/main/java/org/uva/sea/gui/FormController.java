package org.uva.sea.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.model.GuiModel;
import org.uva.sea.gui.model.QuestionModelFactoryImpl;
import org.uva.sea.gui.model.ValueChangeListener;
import org.uva.sea.gui.render2.ErrorRenderer;
import org.uva.sea.gui.render2.QuestionRenderer;
import org.uva.sea.gui.render2.WarningRenderer;
import org.uva.sea.gui.renderer.JavafxRendererVisitor;
import org.uva.sea.ql.interpreter.dataObject.InterpreterResult;
import org.uva.sea.ql.interpreter.dataObject.QuestionData;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.ql.interpreter.exceptions.StaticAnalysisError;
import org.uva.sea.ql.interpreter.staticAnalysis.helpers.Messages;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class FormController implements Initializable, ValueChangeListener {

    private String fileName = "/home/eigenaar/IdeaProjects/endless-ql/Joanna_Laurence/src/main/resources/example.ql";

    private GuiModel guiModel;

    private QuestionRenderer questionRenderer;

    private WarningRenderer warningRenderer;

    private ErrorRenderer errorRenderer;

    @FXML
    private VBox questionBox;

    @FXML
    private VBox messageBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        guiModel = new GuiModel(fileName);
        JavafxRendererVisitor renderer = new JavafxRendererVisitor(questionBox, messageBox, this);
        questionRenderer = new QuestionRenderer(renderer);
        warningRenderer = new WarningRenderer(renderer);
        errorRenderer = new ErrorRenderer(renderer);
        onChange();
    }

    @Override
    public void onChange() {
        try {
            updateGui();
        } catch (IOException | StaticAnalysisError e) {
            errorRenderer.render(e.getMessage());
        }
    }

    private void updateGui() throws IOException, StaticAnalysisError {
        InterpreterResult interpreterResult = guiModel.getInterpreterResult();
        questionRenderer.render(interpreterResult.getQuestions());

        Messages warnings = interpreterResult.getWarnings();
        for(String warning : warnings.getMessages())
            warningRenderer.render(warning);
    }


    @FXML
    public void loadQLFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a QL file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("QL Files", "*.ql"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File qlFile = fileChooser.showOpenDialog(null);
        if (qlFile != null) {
            fileName = qlFile.getAbsolutePath();
            guiModel = new GuiModel(fileName);
        }
    }

    @FXML
    public void export(ActionEvent actionEvent) {
        //TODO: Implement Export function
        System.out.println("Export");
    }



    public void updateGuiModel(String questionName, Value value) {
        guiModel.updateQuestion(questionName, value);
    }

}
