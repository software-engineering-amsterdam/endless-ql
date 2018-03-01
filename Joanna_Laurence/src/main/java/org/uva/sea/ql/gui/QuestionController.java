package org.uva.sea.ql.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import org.uva.sea.ql.DataObject.QuestionData;
import org.uva.sea.ql.QLFormGenerator;
import org.uva.sea.ql.evaluate.SymbolTable;
import org.uva.sea.ql.value.BooleanValue;
import org.uva.sea.ql.value.IntValue;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class QuestionController implements Initializable {

    private static final String FILE_NAME = "/example.ql";
    private List<QuestionGUI> questionGUIs;
    private List<QuestionData> questions;
    private SymbolTable symbolTable = new SymbolTable();
    private QLFormGenerator formGenerator = new QLFormGenerator();

    @FXML
    private VBox vBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateQuestions();
    }

    private void updateQuestions() {
        try {
            questions = formGenerator.generate(getClass().getResource(FILE_NAME).getFile(), symbolTable);
            logQuestions(questions);
            updateQuestionGUIs(questions);
            printQuestions();
        } catch (IOException e) {
            e.printStackTrace();
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
        vBox.getChildren().removeAll(vBox.getChildren());
        for (QuestionGUI questionGUI : questionGUIs) {
            if (questionGUI.isShouldBeVisible()) {
                vBox.getChildren().add(createQuestionRow(questionGUI));
            }
        }
    }

    private Node createQuestionRow(QuestionGUI questionGUI) {
        GridPane wrapper = new GridPane();

        wrapper.getColumnConstraints().add(new ColumnConstraints(350));
        wrapper.getRowConstraints().add(new RowConstraints(40));

        wrapper.add(GUIHelper.printLabel(questionGUI), 0, 0);
        wrapper.add(generateInput(questionGUI), 1, 0);

        return wrapper;
    }

    private Control generateInput(QuestionGUI questionGUI) {
        if (questionGUI.isComputed()) {
            return GUIHelper.printComputedValue(questionGUI);
        } else {
            switch (questionGUI.getType()) {
                case BOOLEAN:
                    CheckBox checkBox = new CheckBox();
                    if (questionGUI.getValue() != null) {
                        checkBox.setSelected(((BooleanValue) questionGUI.getValue()).getBooleanValue());
                    }
                    checkBox.selectedProperty()
                            .addListener((observable, oldValue, newValue) ->
                            {
                                System.out.println("set into " + newValue);
                                symbolTable.addOrUpdateValue(questionGUI.getVariableName(), new BooleanValue(newValue));
                                updateQuestions();
                            });
                    return checkBox;
                case DECIMAL:
                case INTEGER:
                case MONEY:
                case STRING:
                case DATE:
                    TextField textField = new TextField();
                    if (questionGUI.getValue() != null) {
                        textField.setText(questionGUI.displayValue());
                    }
                    textField.setEditable(true);
                    textField.setMinWidth(100.0);
                    textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            if (newValue) {
                                System.out.println("Textfield on focus " + textField.getText());
                            } else {
                                System.out.println("Textfield out focus " + textField.getText());
                                if (!textField.getText().equals("")) {
                                    symbolTable.addOrUpdateValue(questionGUI.getVariableName(), new IntValue(textField.getText()));
                                    updateQuestions();
                                }
                            }
                        }
                    });
                    return textField;
                case UNKNOWN:
                default:
                    return new Label("UNKNOWN");
            }
        }
    }

    private void logQuestions(List<QuestionData> questions) {
        for (QuestionData question : questions) {
            System.out.println(question.getLabel() +
                    " " + question.getQuestionName() +
                    " " + question.getValue() +
                    " " + question.getNodeType().name() +
                    " " + question.isComputed());
        }
    }


}
