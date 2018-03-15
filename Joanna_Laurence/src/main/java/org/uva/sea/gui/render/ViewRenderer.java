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
import org.uva.sea.gui.widget.*;

import java.util.List;

public class ViewRenderer {

    public static final int COLUMN = 350;
    public static final int ROW = 40;
    public static final int MESSAGE_ROW = 600;
    public static final int MESSAGE_COLMN = 40;
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

    public void displayQuestionRow(final BaseQuestionModel questionModel) {
        this.questionBox.getChildren().add(this.createQuestionRow(questionModel));
    }

    public void displayQuestions(final Iterable<BaseQuestionModel> questionGUIs) {
        this.questionBox.getChildren().removeAll(this.questionBox.getChildren());
        for (final BaseQuestionModel questionRow : questionGUIs) {
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

        wrapper.getColumnConstraints().add(new ColumnConstraints(COLUMN));
        wrapper.getRowConstraints().add(new RowConstraints(ROW));

        wrapper.add(createQuestionLabel(questionModel.getLabel()), 0, 0);
        Control widget = createWidget(questionModel);
        if (controller.getLastFocusedQuestion().equals(questionModel.getVariableName())) {
            widget.setFocusTraversable(true);
        } else {
            widget.setFocusTraversable(false);
        }
        wrapper.add(widget, 1, 0);

        return wrapper;
    }

    private Control createWidget(BaseQuestionModel questionModel) {
        Widget widget;
        switch (questionModel.getWidgetType()) {
            case CHECKBOX:
                widget = new CheckBoxWidget();
                break;
            case CHOICEBOX:
                widget = new ChoiceBoxWidget();
                break;
            case RADIO:
                widget = new RadioButtonWidget();
                break;
            case SLIDER:
                widget = new SliderWidget();
                break;
            case SPINBOX:
                widget = new SpinnerWidget();
                break;
            default:
            case TEXTFIELD:
                widget = new TextFieldWidget();
                break;
        }
        return widget.draw(questionModel, controller);
    }

    private Label createQuestionLabel(String string) {
        Label label = new Label(string.replace("\"", ""));
        label.setWrapText(true);
        return label;
    }

    private Node createMessageRow(String message) {
        GridPane wrapper = new GridPane();

        wrapper.getColumnConstraints().add(new ColumnConstraints(MESSAGE_ROW));
        wrapper.getRowConstraints().add(new RowConstraints(MESSAGE_COLMN));

        Label label = new Label(message);
        label.setWrapText(true);
        wrapper.add(label, 0, 0);

        return wrapper;
    }
}
