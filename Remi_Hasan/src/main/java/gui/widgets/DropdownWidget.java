package gui.widgets;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import ql.evaluation.value.Value;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableBoolean;

import java.util.List;

public class DropdownWidget extends ComboBox<String> implements GUIWidget {

    public DropdownWidget(String falseLabel, String trueLabel) {
        this.setItems(FXCollections.observableArrayList(falseLabel, trueLabel));

        // Default to false
        this.getSelectionModel().select(0);
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        this.valueProperty().addListener(invalidationListener);
    }

    @Override
    public Expression getExpressionValue() {
        // Item at index 1 is always the true option, so check if that one is selected
        boolean isTrue = this.getSelectionModel().getSelectedIndex() == 1;
        return new ExpressionVariableBoolean(null, isTrue);
    }

    @Override
    public void setValue(Value value) {
        // Index 1 is true, index 0 is false
        this.getSelectionModel().select(value.getBooleanValue() ? 1 : 0);
    }

    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public void setColor(String color) {

    }

    @Override
    public void setFont(String font) {

    }

    @Override
    public void setFontSize(int fontSize) {

    }

    @Override
    public void setWidth(int width) {

    }
}
