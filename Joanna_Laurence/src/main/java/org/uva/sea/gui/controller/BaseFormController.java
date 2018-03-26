package org.uva.sea.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.controller.utility.AlertPopup;
import org.uva.sea.gui.controller.utility.Exporter;
import org.uva.sea.gui.controller.utility.FileSelector;
import org.uva.sea.gui.model.QuestionModel;
import org.uva.sea.gui.model.RenderingElements;
import org.uva.sea.gui.model.factory.IWidgetFactory;
import org.uva.sea.gui.model.factory.WidgetNotFoundException;
import org.uva.sea.languages.BaseEvaluator;
import org.uva.sea.languages.QlEvaluator;
import org.uva.sea.languages.QlSEvaluator;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;


public abstract class BaseFormController implements Initializable, IQuestionValueUpdatedListener {

    private final QuestionModel questionModel;

    private final Renderer renderer = new Renderer();
    @FXML
    protected TabPane tabPane;
    @FXML
    protected VBox messages;
    @FXML
    VBox container;

    protected BaseFormController(IWidgetFactory questionModel) {
        this.questionModel = new QuestionModel(this, questionModel);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    protected abstract Map<String, VBox> createContainers(EvaluationResult evaluationResult);


    private String askFileLocation(String title, String extension) {
        File selectedFile = FileSelector.getFile(title, extension, "*." + extension);
        if (selectedFile == null) {
            AlertPopup.displayWarning("No file selected");
            return null;
        }

        return selectedFile.getAbsolutePath();
    }

    @FXML
    public void loadQLFile(ActionEvent actionEvent) {
        String qlFile = this.askFileLocation("Load QL file", "ql");
        if (qlFile == null)
            return;

        BaseEvaluator evaluator = new QlEvaluator(qlFile);
        this.updateInterpreter(evaluator);
        this.drawComponents();
    }

    @FXML
    public void loadQLSFile(ActionEvent actionEvent) {
        String qlFile = this.askFileLocation("Load QL file", "ql");
        String qlsFile = this.askFileLocation("Load QLS file", "qls");
        if ((qlFile == null) || (qlsFile == null))
            return;

        BaseEvaluator evaluator = new QlSEvaluator(qlFile, qlsFile);
        this.updateInterpreter(evaluator);
        this.drawComponents();
    }

    private void updateInterpreter(BaseEvaluator evaluator) {
        this.questionModel.setBaseEvaluator(evaluator);
    }

    private void showMessages(RenderingElements questionRenders) {
        for (String warning : questionRenders.getWarnings())
            AlertPopup.displayWarning(warning);

        for (String error : questionRenders.getErrors())
            AlertPopup.displayError(error);
    }

    private void drawComponents() {
        try {
            EvaluationResult evaluationResult = this.questionModel.getEvaluationResults();
            RenderingElements questionRenders = this.questionModel.getQuestionRenders(evaluationResult);
            if (questionRenders == null) {
                AlertPopup.displayError("No questions could be displayed");
                return;
            }

            this.showMessages(questionRenders);
            this.renderer.clearTabPane(this.tabPane);
            Map<String, VBox> panes = this.createContainers(evaluationResult);
            this.renderer.draw(questionRenders.getWidgets(), panes);

        } catch (WidgetNotFoundException | IOException | InterruptedException e) {
            AlertPopup.displayError(e.getMessage());
        }
    }


    @Override
    public void updateGuiVariable(String identifier, Value value) {
        this.questionModel.setVariable(identifier, value);
        this.drawComponents();
        this.renderer.setFocus(identifier);
    }

    @FXML
    public void export(ActionEvent actionEvent) {
        try {
            Exporter exporter = new Exporter();
            String fileName = exporter.saveAnswers(this.questionModel);
            AlertPopup.displayInfo("Saved file in: " + fileName);
        } catch (IOException | InterruptedException e) {
            AlertPopup.displayError(e.getMessage());
        }
    }
}
