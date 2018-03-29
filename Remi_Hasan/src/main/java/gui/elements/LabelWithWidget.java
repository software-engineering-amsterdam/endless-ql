package gui.elements;

import gui.widgets.GUIWidget;
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
        label.setTextFill(Color.web(color));
        guiWidget.setColor(color);
    }

    @Override
    public void setFont(String fontFamily) {
        label.setFont(Font.font(fontFamily, label.getFont().getSize()));
        guiWidget.setFont(fontFamily);
    }

    @Override
    public void setFontSize(int fontSize) {
        label.setFont(new Font(label.getFont().getFamily(), fontSize));
        guiWidget.setFontSize(fontSize);
    }

    @Override
    public void setWidth(int width) {
        guiWidget.setWidth(width);
    }
}
