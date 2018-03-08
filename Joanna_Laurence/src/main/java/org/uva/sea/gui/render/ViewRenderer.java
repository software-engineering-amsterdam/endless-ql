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

    public void displayQuestionRow(BaseQuestionModel questionModel) {
        questionBox.getChildren().add(createQuestionRow(questionModel));
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

    private Control createWidget(BaseQuestionModel questionModel) {
        //TODO: Refactor this to classes to be able to call: widget.draw()
        switch (questionModel.getWidgetType()) {
            case CHECKBOX:
                CheckBox checkBox = new CheckBox();
                if (questionModel.getValue() != null) {
                    System.out.println("Computed boolean value " + questionModel.displayValue());
                    checkBox.setSelected(new BooleanValue(questionModel.displayValue()).getBooleanValue());
                }
                checkBox.selectedProperty()
                        .addListener((observable, oldIsFocused, newIsFocused) ->
                        {
                            controller.updateGuiModel(questionModel.getVariableName(), new BooleanValue(newIsFocused));
                        });
                return checkBox;
            case SLIDER:
            case RADIOBUTTON:
            default:
            case TEXTFIELD:
                TextField textField = this.createTextField(questionModel);
                textField.focusedProperty().addListener((observable, oldIsFocused, newIsFocused) -> {
                    if (!newIsFocused) {
                        TextToValueVisitor textToValueVisitor = new TextToValueVisitor(textField.getText());
                        Value value = questionModel.accept(textToValueVisitor);

                        controller.updateGuiModel(questionModel.getVariableName(), value);
                    }
                });
                return textField;
        }
    }

    private Node createQuestionRow(BaseQuestionModel questionModel) {
        GridPane wrapper = new GridPane();

        wrapper.getColumnConstraints().add(new ColumnConstraints(350));
        wrapper.getRowConstraints().add(new RowConstraints(40));

        wrapper.add(createLabel(questionModel.getLabel()), 0, 0);
        wrapper.add(createWidget(questionModel), 1, 0);

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
}
