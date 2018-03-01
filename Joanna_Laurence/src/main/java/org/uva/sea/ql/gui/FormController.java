package org.uva.sea.ql.gui;

import com.sun.xml.internal.rngom.parse.host.Base;
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
import org.uva.sea.ql.QLFormGenerator;
import org.uva.sea.ql.DataObject.QuestionData;
import org.uva.sea.ql.evaluate.SymbolTable;
import org.uva.sea.ql.gui.model.BaseQuestionRow;
import org.uva.sea.ql.gui.model.BooleanQuestionGUI;
import org.uva.sea.ql.value.BooleanValue;
import org.uva.sea.ql.value.IntValue;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class FormController implements Initializable {

    private static final String FILE_NAME = "/example.ql";
    private List<QuestionData> questions;
    private SymbolTable symbolTable = new SymbolTable();
    private QLFormGenerator formGenerator = new QLFormGenerator();

    private List<BaseQuestionRow> questionModels;


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
        QuestionGuiFactory factory = new QuestionGuiFactory();
        this.questionModels = new ArrayList<>();
        for (QuestionData question : data) {
            BaseQuestionRow questionRow = factory.create(question);
            questionModels.add(questionRow);
        }
    }

    private void printQuestions() {
        vBox.getChildren().removeAll(vBox.getChildren());
        for(BaseQuestionRow questionRow: questionModels){
            vBox.getChildren().add(createQuestionRow(questionRow));
        }
    }

    private Node createQuestionRow(BaseQuestionRow questionRow) {
        GridPane wrapper = new GridPane();

        wrapper.getColumnConstraints().add(new ColumnConstraints(350));
        wrapper.getRowConstraints().add(new RowConstraints(40));

        wrapper.add(GUIHelper.printLabel(questionRow), 0, 0);
        wrapper.add(generateInput(questionRow), 1, 0);

        return wrapper;
    }

    private Control generateInput(BaseQuestionRow questionRow) {
        if (questionRow.isComputed()) {
            return GUIHelper.printComputedValue(questionRow);
        } else {
            switch (questionRow.getType()) {
                case BOOLEAN:
                    CheckBox checkBox = new CheckBox();
                    if (questionRow.getValue() != null) {
                        checkBox.setSelected(((BooleanValue) questionRow.getValue()).getBooleanValue());
                    }
                    checkBox.selectedProperty()
                            .addListener((observable, oldValue, newValue) ->
                            {
                                System.out.println("set into " + newValue);
                                symbolTable.addOrUpdateValue(questionRow.getVariableName(), new BooleanValue(newValue));
                                updateQuestions();
                            });
                    return checkBox;
                case DECIMAL:
                case INTEGER:
                case MONEY:
                case STRING:
                case DATE:
                    TextField textField = new TextField();
                    if (questionRow.getValue() != null) {
                        textField.setText(questionRow.displayValue());
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
                                    symbolTable.addOrUpdateValue(questionRow.getVariableName(), new IntValue(textField.getText()));
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
