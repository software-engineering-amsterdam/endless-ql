package gui.elements.widgets.chooser;

import gui.elements.widgets.GUIWidget;
import javafx.beans.InvalidationListener;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableBoolean;

public class RadioWidget extends HBox implements GUIWidget {

    private final ToggleGroup group;
    private final RadioButton falseButton;
    private final RadioButton trueButton;

    public RadioWidget(String falseLabel, String trueLabel) {
        group = new ToggleGroup();

        falseButton = new RadioButton(falseLabel);
        trueButton = new RadioButton(trueLabel);

        falseButton.setSelected(true);

        trueButton.setToggleGroup(group);
        falseButton.setToggleGroup(group);

        this.getChildren().add(falseButton);
        this.getChildren().add(trueButton);
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        group.selectedToggleProperty().addListener(invalidationListener);
    }

    @Override
    public Expression getExpressionValue() {
        return new ExpressionVariableBoolean(trueButton.isSelected());
    }

    @Override
    public void setValue(Value value) {
        trueButton.setSelected(value.getBooleanValue());
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
