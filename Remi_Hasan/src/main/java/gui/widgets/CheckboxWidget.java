package gui.widgets;

import javafx.beans.InvalidationListener;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableBoolean;

public class CheckboxWidget extends CheckBox implements GUIWidget {

    public CheckboxWidget() {
        this.managedProperty().bind(this.visibleProperty());
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        this.selectedProperty().addListener(invalidationListener);
    }

    @Override
    public Expression getExpressionValue() {
        return new ExpressionVariableBoolean(null, this.isSelected());
    }

    @Override
    public void setValue(Value value) {
        this.setSelected(value.getBooleanValue());
    }

    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public void setColor(String color) {
        this.setStyle(this.getStyle() + "-fx-text-inner-color: " + color + ";");
    }

    @Override
    public void setFont(String font) {
        // Checkbox has no label, so no action
    }

    @Override
    public void setFontSize(int fontSize) {
        // Checkbox has no label, so no action
    }

    @Override
    public void setWidth(int width) {
        this.setStyle(this.getStyle() + "-fx-font-size: " + width + ";");
    }
}
