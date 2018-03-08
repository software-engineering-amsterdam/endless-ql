package org.uva.sea.gui.render;

import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.render.visitor.ModelRenderer;
import org.uva.sea.gui.widget.CheckBoxWidget;
import org.uva.sea.gui.widget.RadioButtonWidget;
import org.uva.sea.gui.widget.SliderWidget;
import org.uva.sea.gui.widget.TextFieldWidget;

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
        messageBox.getChildren().add(createMessageRow(prependMessage + warningMessage));
    }

    private Node createQuestionRow(BaseQuestionModel questionModel) {
        GridPane wrapper = new GridPane();

        wrapper.getColumnConstraints().add(new ColumnConstraints(350));
        wrapper.getRowConstraints().add(new RowConstraints(40));

        wrapper.add(createQuestionLabel(questionModel.getLabel()), 0, 0);
        wrapper.add(createWidget(questionModel), 1, 0);

        return wrapper;
    }

    private Control createWidget(BaseQuestionModel questionModel) {
        switch (questionModel.getWidgetType()) {
            case CHECKBOX:
                return new CheckBoxWidget().draw(questionModel, controller);
            case SLIDER:
                return new SliderWidget().draw(questionModel, controller);
            case RADIOBUTTON:
                return new RadioButtonWidget().draw(questionModel, controller);
            default:
            case TEXTFIELD:
                return new TextFieldWidget().draw(questionModel, controller);
        }
    }

    private Label createQuestionLabel(String string) {
        return new Label(string.replace("\"", ""));
    }

    private Node createMessageRow(String message) {
        GridPane wrapper = new GridPane();

        wrapper.getColumnConstraints().add(new ColumnConstraints(600));
        wrapper.getRowConstraints().add(new RowConstraints(40));

        wrapper.add(new Label(message), 0, 0);

        return wrapper;
    }
}
