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
import org.uva.sea.gui.widget.AbstractWidgetFactory;
import org.uva.sea.gui.widget.DefaultWidgetFactory;
import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;

public class ViewRenderer {

    private static final int COLUMN = 350;
    private static final int ROW = 40;
    private static final int MESSAGE_ROW = 600;
    private static final int MESSAGE_COLUMN = 40;
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
        this.messageBox.getChildren().add(this.createMessageRow(prependMessage + warningMessage));
    }

    private Node createQuestionRow(BaseQuestionModel questionModel) {
        GridPane wrapper = new GridPane();

        wrapper.getColumnConstraints().add(new ColumnConstraints(ViewRenderer.COLUMN));
        wrapper.getRowConstraints().add(new RowConstraints(ViewRenderer.ROW));

        wrapper.add(this.createQuestionLabel(questionModel.getLabel()), 0, 0);

        AbstractWidgetFactory factory = new DefaultWidgetFactory(controller);

        if (questionModel.getWidgetType() == WidgetType.DEFAULT) {
            factory = new DefaultWidgetFactory(controller);
        }
        //TODO: add QlsWidgetFactory
        Control widget = factory.createWidget(questionModel);

        //handle last focused widget
        if (this.controller.getLastFocusedQuestion().equals(questionModel.getVariableName())) {
            widget.setFocusTraversable(true);
        } else {
            widget.setFocusTraversable(false);
        }
        wrapper.add(widget, 1, 0);

        return wrapper;
    }

    private Label createQuestionLabel(String string) {
        Label label = new Label(string.replace("\"", ""));
        label.setWrapText(true);
        return label;
    }

    private Node createMessageRow(String message) {
        GridPane wrapper = new GridPane();

        wrapper.getColumnConstraints().add(new ColumnConstraints(ViewRenderer.MESSAGE_ROW));
        wrapper.getRowConstraints().add(new RowConstraints(ViewRenderer.MESSAGE_COLUMN));

        Label label = new Label(message);
        label.setWrapText(true);
        wrapper.add(label, 0, 0);

        return wrapper;
    }
}
