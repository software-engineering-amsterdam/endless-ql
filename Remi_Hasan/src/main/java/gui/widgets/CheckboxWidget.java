package gui.widgets;

import javafx.beans.InvalidationListener;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import ql.analysis.SymbolTable;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableBoolean;
import qls.model.StyleSheet;

public class CheckboxWidget extends CheckBox implements GUIWidget {

    private final String identifier;private final boolean computed;

    CheckboxWidget(String identifier, boolean computed){
        this.identifier = identifier; this.computed = computed;
    }

    @Override
    public void update(SymbolTable symbolTable) {
        if(computed) setValue(symbolTable.getValue(this.identifier)); else symbolTable.setExpression(identifier, this.getExpressionValue());
    }

    @Override
    public void update(StyleSheet styleSheet) {

    }

    @Override
    public Parent render() {
        return null;
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        if(!computed)
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

    @Override
    public String getIdentifier() {
        return identifier;
    }
}
