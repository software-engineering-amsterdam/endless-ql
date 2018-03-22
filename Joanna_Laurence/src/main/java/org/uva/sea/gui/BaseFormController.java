package org.uva.sea.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.ql.FileSelector;
import org.uva.sea.gui.ql.IGuiElementUpdateListener;
import org.uva.sea.gui.ql.Renderer;
import org.uva.sea.gui.ql.components.AlertBuilder;
import org.uva.sea.gui.ql.model.QuestionModel;
import org.uva.sea.gui.ql.model.RenderElements;
import org.uva.sea.gui.ql.model.factory.WidgetFactory;
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


public abstract class BaseFormController implements Initializable, IGuiElementUpdateListener {

    private final QuestionModel formModel;

    private final AlertBuilder alertBuilder = new AlertBuilder();

    private final Renderer renderer = new Renderer();

    @FXML
    protected VBox container;

    @FXML
    protected TabPane tabPane;

    @FXML
    protected VBox messages;

    public BaseFormController(WidgetFactory formModel) {
        this.formModel = new QuestionModel(this, formModel);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String defaultQlLocation = "/basic.ql";
        String defaultQlsLocation = "/basic.qls";
        String qlFile = BaseFormController.class.getClass().getResource(defaultQlLocation).getFile();
        String qlsFile = BaseFormController.class.getClass().getResource(defaultQlsLocation).getFile();
        BaseEvaluator evaluator = new QlSEvaluator(qlFile, qlsFile);

        this.updateInterpreter(evaluator);
        this.drawComponents();
        //TODO:remove end
    }


    protected abstract Map<String, VBox> createContainer(EvaluationResult evaluationResult);


    private String getFileSelector(String title, String extension) {
        FileSelector fileSelector = new FileSelector(title, extension, "*." + extension);
        File selectedFile = fileSelector.getFile();
        if (selectedFile == null) {
            this.displayWarning("No file selected");
            return null;
        }

        return selectedFile.getAbsolutePath();
    }

    @FXML
    public void loadQLFile(ActionEvent actionEvent) {
        String qlFile = this.getFileSelector("Load QL file", "ql");
        if (qlFile == null)
            return;

        BaseEvaluator evaluator = new QlEvaluator(qlFile);
        this.updateInterpreter(evaluator);
        this.drawComponents();
    }

    @FXML
    public void loadQLSFile(ActionEvent actionEvent) {
        String qlFile = this.getFileSelector("Load QL file", "ql");
        String qlsFile = this.getFileSelector("Load QLS file", "qls");
        if ((qlFile == null) || (qlsFile == null))
            return;

        BaseEvaluator evaluator = new QlSEvaluator(qlFile, qlsFile);
        this.updateInterpreter(evaluator);
        this.drawComponents();
    }

    /**
     * Update the interpreter that is used
     *
     * @param evaluator
     */
    private void updateInterpreter(BaseEvaluator evaluator) {
        this.formModel.setInterpreter(evaluator);
    }


    private void showMessages(RenderElements questionRenders) {
        for (String warning : questionRenders.getWarnings())
            this.displayInfo(warning);

        for (String error : questionRenders.getErrors())
            this.displayError(error);
    }

    /**
     * Redraw the GUI
     */
    private void drawComponents() {
        try {
            EvaluationResult evaluationResult = this.formModel.getEvaluationResults();
            RenderElements questionRenders = this.formModel.getQuestionRenders(evaluationResult);
            if (questionRenders == null) {
                this.displayError("No questions could be displayed");
                return;
            }

            this.showMessages(questionRenders);
            this.renderer.clearTabPane(this.tabPane);
            Map<String, VBox> panes = this.createContainer(evaluationResult);
            this.renderer.draw(questionRenders.getRenderables(), panes);

        } catch (IOException | InterruptedException e) {
            this.displayError(e.getMessage());
        }
    }


    /**
     * Display an error
     *
     * @param message
     */
    private void displayError(String message) {
        this.alertBuilder.buildError(message).show();
    }

    /**
     * Display an information
     *
     * @param message
     */
    private void displayInfo(String message) {
        this.alertBuilder.buildInfo(message).show();
    }

    /**
     * Display a warning
     *
     * @param message
     */
    private void displayWarning(String message) {
        this.alertBuilder.buildWarning(message).show();
    }

    @Override
    public void updateGuiVariable(Control control, String identifier, Value value) {
        this.formModel.setVariable(identifier, value);
        this.drawComponents();
        control.requestFocus();
    }

    @FXML
    public void export(ActionEvent actionEvent) {
        try {
            Exporter exporter = new Exporter();
            String fileName = exporter.saveAnswers(this.formModel);
            this.displayInfo("Saved file in: " + fileName);
        } catch (IOException | InterruptedException e) {
            this.displayError(e.getMessage());
        }
    }
}
