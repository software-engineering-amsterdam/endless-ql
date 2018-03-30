package gui.components.widgets.chooser;

import gui.components.widgets.GUIWidget;
import javafx.beans.InvalidationListener;
import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.constant.BooleanConstant;

public class RadioButtons extends HBox implements GUIWidget {

    private final ToggleGroup group;
    private final javafx.scene.control.RadioButton falseButton;
    private final javafx.scene.control.RadioButton trueButton;

    public RadioButtons(String falseLabel, String trueLabel) {
        this.group = new ToggleGroup();

        this.falseButton = new javafx.scene.control.RadioButton(falseLabel);
        this.trueButton = new javafx.scene.control.RadioButton(trueLabel);

        this.falseButton.setSelected(true);

        this.trueButton.setToggleGroup(group);
        this.falseButton.setToggleGroup(group);

        this.getChildren().add(falseButton);
        this.getChildren().add(trueButton);
    }

    @Override
    public Expression getExpressionValue() {
        return new BooleanConstant(this.trueButton.isSelected());
    }

    @Override
    public void setValue(Value value) {
        this.trueButton.setSelected(value.getBooleanValue());
    }

    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        this.group.selectedToggleProperty().addListener(invalidationListener);
    }

    @Override
    public void setColor(String color) {
        this.trueButton.setTextFill(Color.valueOf(color));
        this.falseButton.setTextFill(Color.valueOf(color));
    }

    @Override
    public void setFont(String font) {
        Font currentFont = this.trueButton.getFont();
        this.trueButton.setFont(Font.font(font, FontWeight.NORMAL, currentFont.getSize()));
        this.falseButton.setFont(Font.font(font, FontWeight.NORMAL, currentFont.getSize()));
    }

    @Override
    public void setFontSize(int fontSize) {
        Font currentFont = this.trueButton.getFont();
        this.trueButton.setFont(Font.font(currentFont.getFamily(), FontWeight.NORMAL, fontSize));
    }

    @Override
    public void setWidth(int width) {
        this.trueButton.setPrefWidth(width / 2);
        this.falseButton.setPrefWidth(width / 2);
    }
}
