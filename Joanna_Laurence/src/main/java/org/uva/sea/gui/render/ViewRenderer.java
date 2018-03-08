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
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.model.BooleanQuestionModel;
import org.uva.sea.gui.render.visitor.ModelRenderer;
import org.uva.sea.gui.render.visitor.TextToValueVisitor;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.BooleanValue;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.Value;

import java.util.List;

public class ViewRenderer {

    private final VBox questionBox;
    private final VBox messageBox;
    private final FormController controller;
    private final ModelRenderer modelRenderer;

    public ViewRenderer(VBox questionBox, VBox messageBox, FormController formController) {
        this.questionBox = questionBox;
        this.messageBox = messageBox;
        this.controller = formController;
        this.modelRenderer = new ModelRenderer(this);
    }

    public void drawQuestionRow(BaseQuestionModel questionModel) {
        //TODO: refactor for widget
        //This is the implementation for a text
        TextField textField = this.createTextField(questionModel);
        textField.focusedProperty().addListener((observable, oldIsFocused, newIsFocused) -> {
            if (!newIsFocused) {
                TextToValueVisitor textToValueVisitor = new TextToValueVisitor(textField.getText());
                Value value = questionModel.accept(textToValueVisitor);

                controller.updateGuiModel(questionModel.getVariableName(), value);
            }
        });

        questionBox.getChildren().add(createQuestionRow(createLabel(questionModel.getLabel()), textField));
    }

    public void drawBooleanQuestionRow(BooleanQuestionModel questionModel) {
        //TODO: add widget support. Remove this because this is a different widget
        //This is the implementation for a checkbox
        CheckBox checkBox = new CheckBox();
        if (questionModel.getValue() != null) {
            checkBox.setSelected(questionModel.getBasicValue());
        }

        checkBox.selectedProperty()
                .addListener((observable, oldIsFocused, newIsFocused) ->
                {
                    System.out.println("Checkbox set into " + newIsFocused + " " + questionModel.getVariableName());
                    controller.updateGuiModel(questionModel.getVariableName(), new BooleanValue(newIsFocused));
                });

        questionBox.getChildren().add(createQuestionRow(createLabel(questionModel.getLabel()), checkBox));
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

    private Label createLabel(String string) {
        return new Label(string.replace("\"", ""));
    }

    private TextField createTextField(BaseQuestionModel question) {
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
            questionRow.accept(this.modelRenderer);
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
