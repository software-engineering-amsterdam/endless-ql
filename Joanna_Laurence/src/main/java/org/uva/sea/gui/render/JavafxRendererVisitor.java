package org.uva.sea.gui.render;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.*;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.BooleanValue;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.Value;

import java.util.List;

//TODO: Handle situation when newInput.getText().equals("")
public class JavafxRendererVisitor implements QuestionModelVisitor {

    private final VBox questionBox;
    private final VBox messageBox;
    private final FormController controller;

    public JavafxRendererVisitor(VBox questionBox, VBox messageBox, FormController formController) {
        this.questionBox = questionBox;
        this.messageBox = messageBox;
        this.controller = formController;
    }

    @Override
    public Void visit(BooleanQuestionModel question) {
        CheckBox checkBox = new CheckBox();
        if (question.getValue() != null) {
            checkBox.setSelected(question.getBasicValue());
        }
        checkBox.selectedProperty()
                .addListener((observable, oldIsFocused, newIsFocused) ->
                {
                    System.out.println("Checkbox set into " + newIsFocused + " " + question.getVariableName());
                    controller.updateGuiModel(question.getVariableName(), new BooleanValue(newIsFocused));
                });
        questionBox.getChildren().add(createQuestionRow(printLabel(question.getLabel()), checkBox));
        return null;
    }

    @Override
    public Void visit(DateQuestionModel question) {
        TextField newInput = printTextField(question);
        addGUIListener(question, newInput);
        return null;
    }

    @Override
    public Void visit(DecimalQuestionModel question) {
        TextField newInput = printTextField(question);
        addGUIListener(question, newInput);
        return null;
    }

    @Override
    public Void visit(ErrorQuestionModel question) {
        displayError(question.displayValue());
        return null;
    }

    @Override
    public Void visit(IntQuestionModel question) {
        TextField newInput = printTextField(question);
        addGUIListener(question, newInput);
        return null;
    }

    @Override
    public Void visit(MoneyQuestionModel question) {
        TextField newInput = printTextField(question);
        addGUIListener(question, newInput);
        return null;
    }

    @Override
    public Void visit(StringQuestionModel question) {
        TextField newInput = printTextField(question);
        addGUIListener(question, newInput);
        return null;
    }

    private void addGUIListener(BaseQuestionModel questionModel, TextField textField) {
        textField.focusedProperty().addListener((observable, oldIsFocused, newIsFocused) -> {
            if (!newIsFocused) {
                TextToValueVisitor textToValueVisitor = new TextToValueVisitor(textField.getText());
                Value value = questionModel.accept(textToValueVisitor);

                controller.updateGuiModel(questionModel.getVariableName(), value);
            }
        });

        questionBox.getChildren().add(createQuestionRow(printLabel(questionModel.getLabel()), textField));
    }


    private Node createQuestionRow(Label label, Control input) {
        GridPane wrapper = new GridPane();

        wrapper.getColumnConstraints().add(new ColumnConstraints(350));
        wrapper.getRowConstraints().add(new RowConstraints(40));

        wrapper.add(label, 0, 0);
        wrapper.add(input, 1, 0);

        return wrapper;
    }

    private Node createMessageRow(Label label) {
        GridPane wrapper = new GridPane();

        wrapper.getColumnConstraints().add(new ColumnConstraints(600));
        wrapper.getRowConstraints().add(new RowConstraints(40));

        wrapper.add(label, 0, 0);

        return wrapper;
    }

    private Label printLabel(String string) {
        return new Label(string.replace("\"", ""));
    }

    private TextField printTextField(BaseQuestionModel question) {
        TextField textField = new TextField();
        if (question.getValue() != null) {
            textField.setText(question.displayValue());
        }
        textField.setEditable(true);
        textField.setMinWidth(100.0);

        if (question.isComputed()) {
            textField.setEditable(false);
        }

        return textField;
    }

    public void displayQuestions(List<BaseQuestionModel> questionGUIs) {
        questionBox.getChildren().removeAll(questionBox.getChildren());
        for (BaseQuestionModel questionRow : questionGUIs) {
            questionRow.accept(this);
        }
    }

    public void displayWarning(String message) {
        this.displayMessage("Warning: ", message);
    }

    public void displayError(String message) {
        this.displayMessage("Error: ", message);
    }

    private void displayMessage(String prependMessage, String warningMessage) {
        messageBox.getChildren().add(createMessageRow(new Label(prependMessage + warningMessage)));
    }
}
