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
import org.uva.sea.gui.renderer.JavafxRendererVisitor;
import org.uva.sea.ql.interpreter.dataObject.QuestionData;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.ql.interpreter.exceptions.StaticAnalysisError;
import org.uva.sea.ql.interpreter.staticAnalysis.helpers.Messages;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class FormController implements Initializable {

    private String fileName = "/Users/joannaroczniak/Desktop/UvA/endless-ql/Joanna_Laurence/src/main/resources/example.ql";
    private List<BaseQuestionModel> questionGUIs;
    private Messages messages;
    private GuiModel guiModel;

    @FXML
    private VBox questionBox;
    @FXML
    private VBox warningBox;

    private JavafxRendererVisitor renderer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            guiModel = new GuiModel(fileName);
            renderer = new JavafxRendererVisitor(questionBox, warningBox, this);
        } catch (IOException | StaticAnalysisError e) {
            e.printStackTrace();
            renderer.alertError(e.getMessage());
        }
        guiModel.attachListener(new QuestionObserver());
        initializeInterpreterResultOnGUI();
    }

    private void initializeInterpreterResultOnGUI() {
        initializeQuestionGUIs(guiModel.getInterpreterResult().getQuestions(), questionBox);
        initializeWarningsOnGUI(guiModel.getInterpreterResult().getWarnings(), warningBox);
        logQuestions(questionGUIs);
    }

    private void initializeQuestionGUIs(List<QuestionData> data, Pane questionBox) {
        QuestionModelFactoryImpl factory = new QuestionModelFactoryImpl();
        this.questionGUIs = new ArrayList<>();
        for (QuestionData question : data) {
            BaseQuestionModel questionRow = factory.create(question);
            questionGUIs.add(questionRow);
        }
        displayQuestions(questionBox);
    }

    private void displayQuestions(Pane vBox) {
        vBox.getChildren().removeAll(vBox.getChildren());
        for (BaseQuestionModel questionRow : questionGUIs) {
            renderer.render(questionRow);
        }
    }

    private void initializeWarningsOnGUI(Messages warnings, VBox warningBox) {
        warningBox.getChildren().removeAll(warningBox.getChildren());
        for (String warningMessage : warnings.getMessages()) {
            renderer.render(warningMessage);
        }
    }

    private void logQuestions(List<BaseQuestionModel> questions) {
        for (BaseQuestionModel question : questions) {
            System.out.println(question.getLabel() +
                    " " + question.getVariableName() +
                    " " + question.displayValue() +
                    " isComputed: " + question.isComputed());
        }
        System.out.println("\n");
    }

    public void updateGuiModel(String questionName, Value value) throws IOException, StaticAnalysisError {
        guiModel.updateQuestion(questionName, value);
    }

    @FXML
    public void loadQLFile(ActionEvent actionEvent) {
        System.out.println("Load QL file");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a QL file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("QL Files", "*.ql"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File qlFile = fileChooser.showOpenDialog(null);
        if (qlFile != null) {
            fileName = qlFile.getAbsolutePath();
            try {
                guiModel.loadNewForm(fileName);
            } catch (IOException | StaticAnalysisError e) {
                e.printStackTrace();
                renderer.alertError(e.getMessage());
            }

            System.out.println(qlFile.getAbsolutePath());
        }
    }

    @FXML
    public void export(ActionEvent actionEvent) {
        //TODO: Implement Export function
        System.out.println("Export");
    }

    private class QuestionObserver implements ValueChangeListener {
        @Override
        public void onChange() {
            initializeInterpreterResultOnGUI();
            System.out.println("Listener on change");
        }
    }
}
