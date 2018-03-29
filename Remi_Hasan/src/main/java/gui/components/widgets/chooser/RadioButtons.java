package gui.components.widgets.chooser;

import gui.components.widgets.GUIWidget;
import javafx.beans.InvalidationListener;
import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.constant.BooleanConstant;

public class RadioButtons extends HBox implements GUIWidget {

    private final ToggleGroup group;
    private final javafx.scene.control.RadioButton falseButton;
    private final javafx.scene.control.RadioButton trueButton;

    public RadioButtons(String falseLabel, String trueLabel) {
        group = new ToggleGroup();

        falseButton = new javafx.scene.control.RadioButton(falseLabel);
        trueButton = new javafx.scene.control.RadioButton(trueLabel);

        falseButton.setSelected(true);

        trueButton.setToggleGroup(group);
        falseButton.setToggleGroup(group);

        this.getChildren().add(falseButton);
        this.getChildren().add(trueButton);
    }

    @Override
    public Expression getExpressionValue() {
        return new BooleanConstant(trueButton.isSelected());
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
    public void setChangeListener(InvalidationListener invalidationListener) {
        group.selectedToggleProperty().addListener(invalidationListener);
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
