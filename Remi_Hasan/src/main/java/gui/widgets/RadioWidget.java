package gui.widgets;

import javafx.beans.InvalidationListener;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import ql.analysis.SymbolTable;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableBoolean;
import qls.model.StyleSheet;

public class RadioWidget extends HBox implements GUIWidget {

    private final String identifier;
    private final boolean computed;
    private final ToggleGroup group;
    private final RadioButton falseButton;
    private final RadioButton trueButton;

    public RadioWidget(String identifier, boolean computed, String falseLabel, String trueLabel) {
        this.identifier = identifier;
        this.computed = computed;
        this.group = new ToggleGroup();

        this.falseButton = new RadioButton(falseLabel);
        this.trueButton = new RadioButton(trueLabel);

        this.falseButton.setSelected(true);

        this.trueButton.setToggleGroup(this.group);
        this.falseButton.setToggleGroup(this.group);

        this.getChildren().add(this.falseButton);
        this.getChildren().add(this.trueButton);
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        if (!computed)
            group.selectedToggleProperty().addListener(invalidationListener);
    }

    @Override
    public Expression getExpressionValue() {
        return new ExpressionVariableBoolean(null, trueButton.isSelected());
    }

    @Override
    public void setValue(Value value) {
        trueButton.setSelected(value.getBooleanValue());
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

    @Override
    public void update(SymbolTable symbolTable) {
        if (computed) setValue(symbolTable.getValue(this.identifier));
        else symbolTable.setExpression(identifier, this.getExpressionValue());
    }

    @Override
    public void update(StyleSheet styleSheet) {

    }

    @Override
    public Parent render() {
        return this;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }
}
