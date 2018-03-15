package org.uva.sea.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.model.GuiModel;
import org.uva.sea.gui.render.*;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FormController implements Initializable {

    private String defaultQlLocation = "/example.ql";
//    private String defaultQlLocation = "/basicQuestions.ql";
    private String defaultQlsLocation = "/basic.qls";
//    private String defaultQlsLocation = "/test.qls";

    private GuiModel guiModel;

    private QuestionRenderer questionRenderer;

    private WarningRenderer warningRenderer;

    private ErrorRenderer errorRenderer;

    private String lastFocusedQuestion = "";

    private String qlFile;
    private String qlsFile;

    @FXML
    private VBox questionBox;
    @FXML
    private VBox messageBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        qlFile = getClass().getResource(defaultQlLocation).getFile();
        qlsFile = getClass().getResource(defaultQlsLocation).getFile();
        guiModel = new GuiModel(qlFile, qlsFile);
        ViewRenderer renderer = new ViewRenderer(questionBox, messageBox, this);
        questionRenderer = new QuestionRenderer(renderer);
        warningRenderer = new WarningRenderer(renderer);
        errorRenderer = new ErrorRenderer(renderer);
        drawGui();
    }

    private void drawGui() {
        try {
            updateGui();
        } catch (InterruptedException | IOException e) {
            errorRenderer.render(e.getMessage());
        }
    }

    private void updateGui() throws IOException, InterruptedException {
        EvaluationResult interpreterResult = guiModel.getInterpreterResult();
        questionRenderer.render(interpreterResult.getQuestions());

        Messages warnings = interpreterResult.getMessages();
        for (String warning : warnings.getMessage(MessageTypes.WARNING))
            warningRenderer.render(warning);
    }


    @FXML
    public void loadQLFile(ActionEvent actionEvent) {
        FileSelector fileSelector = new FileSelector("Load QL file", "QL", "*.ql");
        File qlFile = fileSelector.getFile();

        if (qlFile == null) {
            errorRenderer.render("File not found");
            return;
        }

        guiModel = new GuiModel(qlFile.getAbsolutePath(), null);
        drawGui();
    }

    @FXML
    public void loadQLSFile(ActionEvent actionEvent) {
        FileSelector fileSelector = new FileSelector("Load QLS file", "QLS", "*.qls");
        File qlsFile = fileSelector.getFile();

        if (qlsFile == null) {
            errorRenderer.render("File not found");
            return;
        }

        guiModel = new GuiModel(new File(qlFile).getAbsolutePath(), qlsFile.getAbsolutePath());
        drawGui();
    }

    @FXML
    public void export(ActionEvent actionEvent) {
        //TODO: Implement Export function
        System.out.println("Export");
    }

    public void updateGuiModel(String questionName, Value value) {
        guiModel.updateQuestion(questionName, value);
        drawGui();
    }

    public void setLastFocused(String variableName) {
        this.lastFocusedQuestion = variableName;
    }

    public String getLastFocusedQuestion() {
        return lastFocusedQuestion;
    }
}
