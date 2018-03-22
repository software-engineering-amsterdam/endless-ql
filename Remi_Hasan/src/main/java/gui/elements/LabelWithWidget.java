package gui.elements;

import gui.widgets.GUIWidget;
import javafx.beans.InvalidationListener;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;

// TODO: separate interface
public class LabelWithWidget extends VBox implements GUIQuestionElement {

    private final GUIWidget guiWidget;

    public LabelWithWidget(Label label, GUIWidget guiWidget) {
        this.guiWidget = guiWidget;

        this.getChildren().add(label);
        this.getChildren().add(guiWidget.getNode());
    }

    @Override
    public Expression getExpressionValue() {
        return guiWidget.getExpressionValue();
    }

    @Override
    public void setValue(Value value) {
        guiWidget.setValue(value);
    }

    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        guiWidget.setChangeListener(invalidationListener);
    }

    @Override
    public void setVisibility(boolean visible) {
        this.setVisible(visible);
    }

    @Override
    public void setColor(String color) {
        // TODO: label as well?
        guiWidget.setColor(color);
    }

    @Override
    public void setFont(String font) {
        guiWidget.setFont(font);
    }

    @Override
    public void setFontSize(int fontSize) {
        guiWidget.setFontSize(fontSize);
    }

    @Override
    public void setWidth(int width) {
        guiWidget.setWidth(width);
    }
}
