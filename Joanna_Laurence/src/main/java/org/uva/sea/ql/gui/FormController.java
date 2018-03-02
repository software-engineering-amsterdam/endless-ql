package org.uva.sea.ql.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.uva.sea.ql.DataObject.QuestionData;
import org.uva.sea.ql.gui.model.BaseQuestionModel;
import org.uva.sea.ql.gui.model.QuestionModel;
import org.uva.sea.ql.gui.model.QuestionModelFactoryImpl;
import org.uva.sea.ql.gui.model.ValueChangeListener;
import org.uva.sea.ql.gui.renderer.JavafxRendererVisitor;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class FormController implements Initializable {

    private static final String FILE_NAME = "/example.ql";
    private List<BaseQuestionModel> questionGUIs;
    private QuestionModel questionModels;

    @FXML
    private VBox vBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questionModels = new QuestionModel(FILE_NAME);
        questionModels.attachListener(new QuestionObserver());
        updateQuestions();
    }

    private void updateQuestions() {
        updateQuestionGUIs(questionModels.getQuestions());
        logQuestions(questionGUIs);
        printQuestions();
    }

    private void updateQuestionGUIs(List<QuestionData> data) {
        QuestionModelFactoryImpl factory = new QuestionModelFactoryImpl();
        this.questionGUIs = new ArrayList<>();
        for (QuestionData question : data) {
            BaseQuestionModel questionRow = factory.create(question);
            questionGUIs.add(questionRow);
        }
    }

    private void printQuestions() {
        JavafxRendererVisitor renderer = new JavafxRendererVisitor(vBox, questionModels);
        vBox.getChildren().removeAll(vBox.getChildren());
        for (BaseQuestionModel questionRow : questionGUIs) {
            renderer.render(questionRow);
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

    private class QuestionObserver implements ValueChangeListener {
        @Override
        public void onChange() {
            updateQuestions();
            System.out.println("Listener on change");
        }
    }
}
