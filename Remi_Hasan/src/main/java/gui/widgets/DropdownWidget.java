package gui.widgets;

import javafx.beans.InvalidationListener;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import ql.evaluation.value.Value;
import ql.model.Question;
import ql.model.expression.Expression;

import java.util.List;

public class DropdownWidget extends ComboBox<String> implements GUIWidget {

    private final List<String> options;
    private final Question question;

    public DropdownWidget(Question question, List<String> options) {
        this.question = question;
        this.managedProperty().bind(this.visibleProperty());
        this.options = options;
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        this.valueProperty().addListener(invalidationListener);
    }

    @Override
    public Expression getExpressionValue() {
        // TODO: implement
        return null;
    }

    @Override
    public void setValue(Value value) {
        // TODO: implement
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
