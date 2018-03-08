package org.uva.sea.gui.renderer;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.uva.sea.gui.FormController;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.*;
import org.uva.sea.gui.model.*;
import org.uva.sea.ql.interpreter.exceptions.StaticAnalysisError;

import java.io.IOException;
import java.util.Arrays;
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
    public void visit(BooleanQuestionModel question) {
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
    }

    @Override
    public void visit(DateQuestionModel question) {
        TextField newInput = printTextField(question);
        DateValue value = new DateValue(newInput.getText());
        addGUIListener(question, newInput, value);
    }

    @Override
    public void visit(DecimalQuestionModel question) {
        TextField newInput = printTextField(question);
        DecimalValue value = new DecimalValue(newInput.getText());
        addGUIListener(question, newInput, value);
    }

    @Override
    public void visit(ErrorQuestionModel question) {
        displayErrors(Arrays.asList(question.displayValue()));
    }

    @Override
    public void visit(IntQuestionModel question) {
        TextField newInput = printTextField(question);
        if (newInput.getText().equals("")) {
            displayError("Value cannot be an empty string");
            System.out.println("Error");
        } else {
            IntValue value = new IntValue(newInput.getText());
            addGUIListener(question, newInput, value);
        }
    }

    @Override
    public void visit(MoneyQuestionModel question) {
        TextField newInput = printTextField(question);
        MoneyValue value = new MoneyValue(newInput.getText());
        addGUIListener(question, newInput, value);
    }

    @Override
    public void visit(StringQuestionModel question) {
        TextField newInput = printTextField(question);
        StringValue value = new StringValue(newInput.getText());
        addGUIListener(question, newInput, value);
    }

    private void addGUIListener(BaseQuestionModel questionModel, TextField textField, Value value) {
        textField.focusedProperty().addListener((observable, oldIsFocused, newIsFocused) -> {
            if (!newIsFocused) {
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

    public void displayWarnings(List<String> warnings) {
        for (String warning : warnings) {
            displayWarning(warning);
        }
    }

    public void displayErrors(List<String> errors) {
        for (String error : errors) {
            displayError(error);
        }
    }

    public void displayWarning(String message) {
        this.displayMessage("Warning: ", message);
    }

    public void displayError(String message) {
        this.displayMessage("Error: ", message);
    }

    private void displayMessage(String prependMessage, String warningMessage) {
        messageBox.getChildren().add(new Label(prependMessage + warningMessage));
    }
}
