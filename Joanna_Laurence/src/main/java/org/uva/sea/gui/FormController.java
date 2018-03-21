package org.uva.sea.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.render.*;
import org.uva.sea.languages.QlSEvaluator;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FormController implements Initializable {

    private final String defaultQlLocation = "/example.ql";
//    private final String defaultQlLocation = "/basicQuestions.ql";
    private final String defaultQlsLocation = "/basic.qls";
//    private final String defaultQlsLocation = "/test.qls";

    private QlSEvaluator evaluator;

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
        this.qlFile = this.getClass().getResource(this.defaultQlLocation).getFile();
        this.qlsFile = this.getClass().getResource(this.defaultQlsLocation).getFile();
        this.evaluator = new QlSEvaluator(this.qlFile, this.qlsFile);
        ViewRenderer renderer = new ViewRenderer(this.questionBox, this.messageBox, this);
        this.questionRenderer = new QuestionRenderer(renderer);
        this.warningRenderer = new WarningRenderer(renderer);
        this.errorRenderer = new ErrorRenderer(renderer);
        this.drawGui();
    }

    private void drawGui() {
        try {
            this.updateGui();
        } catch (InterruptedException | IOException e) {
            this.errorRenderer.render(e.getMessage());
        }
    }

    private void updateGui() throws IOException, InterruptedException {
        EvaluationResult interpreterResult = this.evaluator.getQuestions();
        this.questionRenderer.render(interpreterResult.getQuestions());

        Messages warnings = interpreterResult.getMessages();
        for (String warning : warnings.getMessage(MessageTypes.WARNING))
            this.warningRenderer.render(warning);
    }


    @FXML
    public void loadQLFile(ActionEvent actionEvent) {
        FileSelector fileSelector = new FileSelector("Load QL file", "QL", "*.ql");
        File selectedFile = fileSelector.getFile();
        if (selectedFile == null) {
            this.errorRenderer.render("File not found");
            return;
        }

        String qlFile = selectedFile.getAbsolutePath();
        useNewGUISpecification(qlFile, null);
    }

    @FXML
    public void loadQLSFile(ActionEvent actionEvent) {
        FileSelector fileSelector = new FileSelector("Load QLS file", "QLS", "*.qls");
        File selectedFile = fileSelector.getFile();
        if (selectedFile == null) {
            this.errorRenderer.render("File not found");
            return;
        }
        String qlsFile = selectedFile.getAbsolutePath();
        useNewGUISpecification(this.qlFile, qlsFile);
    }

    private void useNewGUISpecification(String qlFileLocation, String qlsFile) {
        this.evaluator = new QlSEvaluator(qlFileLocation, qlsFile);
        this.drawGui();
    }

    @FXML
    public void export(ActionEvent actionEvent) {
        //TODO: Implement Export function
        System.out.println("Export");
    }

    public void updateGuiModel(final String questionName, final Value value) {
        this.evaluator.setVariable(questionName, value);
        this.drawGui();
    }

    public void setLastFocused(String variableName) {
        this.lastFocusedQuestion = variableName;
    }

    public String getLastFocusedQuestion() {
        return this.lastFocusedQuestion;
    }
}
