package org.uva.sea.ql.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import org.uva.sea.ql.Errors;
import org.uva.sea.ql.dataObject.QuestionData;
import org.uva.sea.ql.QLFormGenerator;
import org.uva.sea.ql.evaluate.SymbolTable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class QuestionController implements Initializable {

    private List<QuestionGUI> questionGUIs;
    private List<QuestionData> questions;
    private SymbolTable symbolTable = new SymbolTable();

    @FXML
    private VBox vBox;

    @FXML
    private void addCheckBox(ActionEvent event) {
        vBox.getChildren().add(createBooleanQuestion("Did you sell a house in 2012?"));
    }

    @FXML
    private void addTextField(ActionEvent event) {
        vBox.getChildren().add(createNumericQuestion("What was the selling price?"));
    }

    @FXML
    public void addLabel(ActionEvent actionEvent) {
        vBox.getChildren().add(createComputedQuestion("You sell a house in: "));
    }

    @FXML
    public void addNewQuestion(ActionEvent actionEvent) {
        vBox.getChildren().add(createQuestionRow(new Label("Did you sell a house?"), new CheckBox()));
    }

    @FXML
    private void printQuestions(ActionEvent actionEvent) {
        for (QuestionGUI questionGUI : questionGUIs) {
            vBox.getChildren().add(createQuestionRow(questionGUI));
        }
    }

    private Node createBooleanQuestion(String question) {
        CheckBox checkBox = new CheckBox();
        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println("Checkbox changed! " + question);
            }
        });
        return createQuestionRow(new Label(question), checkBox);
    }

    private Node createNumericQuestion(String question) {
        TextField textField = new TextField();
        textField.setEditable(true);
        textField.setMinWidth(100.0);
        return createQuestionRow(new Label(question), textField);
    }

    private Node createComputedQuestion(String question) {
        Label label = new Label("2012");
        return createQuestionRow(new Label(question), label);
    }

    private Node createQuestionRow(QuestionGUI questionGUI) {
        GridPane wrapper = new GridPane();

        wrapper.getColumnConstraints().add(new ColumnConstraints(350));
        wrapper.getRowConstraints().add(new RowConstraints(40));

        wrapper.add(questionGUI.getLabel(), 0, 0);
        wrapper.add(questionGUI.getType(), 1, 0);

        return wrapper;
    }

    private Node createQuestionRow(final Node label, final Node input) {
        GridPane wrapper = new GridPane();

        wrapper.getColumnConstraints().add(new ColumnConstraints(350));
        wrapper.getRowConstraints().add(new RowConstraints(40));

        wrapper.add(label, 0, 0);
        wrapper.add(input, 1, 0);

        return wrapper;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            QLFormGenerator formGenerator = new QLFormGenerator();
            questions = formGenerator.generate("/example.ql", symbolTable);
            for (QuestionData question : questions) {
                System.out.println(question.getLabel() +
                        " " + question.getValue() +
                        " " + question.getNodeType().name() +
                        " " + question.isComputed());
            }

            updateQuestionGUIs(questions);
            printQuestions();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Errors errors) {
            System.err.println(errors.getMessage());
        }
    }

    private void updateQuestionGUIs(List<QuestionData> data) {
        this.questionGUIs = new ArrayList<>();
        for (QuestionData question : data) {
            QuestionGUI questionGUI = new QuestionGUI(question);
            questionGUIs.add(questionGUI);
        }
    }

    private void printQuestions() {
        for (QuestionGUI questionGUI : questionGUIs) {
            if (questionGUI.isShouldBeVisible()) {
                vBox.getChildren().add(createQuestionRow(questionGUI));
            }
        }
    }
}
