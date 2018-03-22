package org.uva.sea.gui;

import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.ql.FileSelector;
import org.uva.sea.gui.ql.IGuiElementUpdateListener;
import org.uva.sea.gui.ql.Renderer;
import org.uva.sea.gui.ql.components.AlertBuilder;
import org.uva.sea.gui.ql.model.RenderElements;
import org.uva.sea.gui.ql.model.QuestionModel;
import org.uva.sea.languages.BaseEvaluator;
import org.uva.sea.languages.QlEvaluator;
import org.uva.sea.languages.QlSEvaluator;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.*;


public abstract class FormController implements Initializable, IGuiElementUpdateListener {

    private final QuestionModel formModel = new QuestionModel(this);

    private final AlertBuilder alertBuilder = new AlertBuilder();

    private final Renderer renderer = new Renderer();

    @FXML
    private VBox container;
    @FXML
    private TabPane tabPane;
    @FXML
    private VBox messages;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //TODO:remove
        String qlFile = "/home/eigenaar/IdeaProjects/endless-ql/Joanna_Laurence/src/main/resources/basic.ql";
        //String qlFile = "/Users/joannaroczniak/Desktop/UvA/endless-ql/Joanna_Laurence/src/main/resources/basic.ql";
        String qlsFile = "/home/eigenaar/IdeaProjects/endless-ql/Joanna_Laurence/src/main/resources/basic.qls";
        //String qlsFile = "/Users/joannaroczniak/Desktop/UvA/endless-ql/Joanna_Laurence/src/main/resources/basic.qls";

        BaseEvaluator evaluator = new QlSEvaluator(qlFile, qlsFile);

        this.updateInterpreter(evaluator);
        this.drawComponents();
        //TODO:remove end
    }


    protected abstract Map<String, Pane> createPanes(EvaluationResult evaluationResult);


    private String getFileSelector(String title, String extension) {
        FileSelector fileSelector = new FileSelector(title, extension, "*." + extension);
        File selectedFile = fileSelector.getFile();
        if (selectedFile == null) {
            this.displayWarning("Warning: no file selected");
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
        for(String warning : questionRenders.getWarnings())
            this.displayInfo(warning);

        for(String error : questionRenders.getErrors())
            this.displayError(error);
    }

    /**
     * Redraw the GUI
     */
    private void drawComponents() {
        try {
            EvaluationResult evaluationResult = this.formModel.getEvaluationResults();
            RenderElements questionRenders = this.formModel.getQuestionRenders(evaluationResult) ;
            if(questionRenders == null) {
                this.displayError("No questions could be displayed");
                return;
            }

            this.showMessages(questionRenders);

            Map<String, Pane> panes = this.createPanes(evaluationResult);
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
            File file = new FileSelector("Choose file to save", "JSON", "*.json").getFile();
            Writer writer = new FileWriter(file.getAbsolutePath());
            String json = new GsonBuilder().create().toJson(this.createObjectToSave(this.formModel));
            writer.write(json);
            writer.close();
            this.displayInfo("Saved file in: " + file.getName());
        } catch (IOException | InterruptedException e) {
            this.displayError(e.getMessage());
        }
    }

    /**
     * Create a HashMap with given answers to questions.
     *
     * @param questionModel questions to save
     * @return HashMap with key as a question label and value as an answer
     */
    private HashMap<String, String> createObjectToSave(QuestionModel questionModel) throws IOException, InterruptedException {
        HashMap<String, String> hashMap = new HashMap<>();

        if (questionModel == null)
            return hashMap;

        for (QuestionData questionData : questionModel.getEvaluationResults().getQuestions()) {
            String value;
            if (questionData.getValue() == null) {
                value = "null";
            } else {
                value = questionData.getValue().toString();
            }
            hashMap.put(questionData.getLabel().replace("\"", ""), value.replace("\"", ""));
        }
        return hashMap;
    }
}
