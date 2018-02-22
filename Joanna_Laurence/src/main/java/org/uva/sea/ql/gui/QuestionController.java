package org.uva.sea.ql.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;


public class QuestionController {

    @FXML
    private VBox vBox;

    @FXML
    private void addCheckBox(ActionEvent event) {
        vBox.getChildren().add(createBooleanQuestion("Did you sell a house in 2012?"));
    }

    @FXML
    private void addTextField(ActionEvent event) {
        vBox.getChildren().add(createNumericQuestion("Did you sell a house in 2012?"));
    }

    @FXML
    public void addLabel(ActionEvent actionEvent) {
        vBox.getChildren().add(createComputedQuestion("Did you sell a house in 2012?"));
    }

    public void addNewQuestion(ActionEvent actionEvent) {
        vBox.getChildren().add(createQuestionRow(new Label("Did you sell a house?"), new CheckBox()));
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
        Label label = new Label("123");
        return createQuestionRow(new Label(question), label);
    }

    private Node createQuestionRow(final Node label, final Node input) {
        GridPane wrapper = new GridPane();

        wrapper.getColumnConstraints().add(new ColumnConstraints(350));
        wrapper.getRowConstraints().add(new RowConstraints(40));

        wrapper.add(label, 0, 0);
        wrapper.add(input, 1, 0);

        return wrapper;
    }


}
