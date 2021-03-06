package gui.components;

import gui.components.widgets.GUIWidget;
import javafx.beans.InvalidationListener;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;

public class LabelWithWidget extends VBox implements ILabelWithWidget {

    private final Label label;
    private final GUIWidget guiWidget;

    public LabelWithWidget(Label label, GUIWidget guiWidget) {
        this.label = label;
        this.guiWidget = guiWidget;

        this.getChildren().add(label);
        this.getChildren().add(guiWidget.getNode());

        // This way, when hiding the widget, it will also take no space in the GUI
        this.managedProperty().bind(this.visibleProperty());
    }

    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public Expression getExpressionValue() {
        return this.guiWidget.getExpressionValue();
    }

    @Override
    public void setValue(Value value) {
        this.guiWidget.setValue(value);
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        this.guiWidget.setChangeListener(invalidationListener);
    }

    @Override
    public void setVisibility(boolean visible) {
        this.setVisible(visible);
    }

    @Override
    public void setColor(String color) {
        this.label.setTextFill(Color.web(color));
        this.guiWidget.setColor(color);
    }

    @Override
    public void setFont(String fontFamily) {
        this.label.setFont(Font.font(fontFamily, label.getFont().getSize()));
        this.guiWidget.setFont(fontFamily);
    }

    @Override
    public void setFontSize(int fontSize) {
        this.label.setFont(new Font(this.label.getFont().getFamily(), fontSize));
        this.guiWidget.setFontSize(fontSize);
    }

    @Override
    public void setWidth(int width) {
        this.guiWidget.setWidth(width);
    }
}
