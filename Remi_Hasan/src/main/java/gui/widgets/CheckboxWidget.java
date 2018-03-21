package gui.widgets;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableBoolean;
import ql.model.expression.variable.ExpressionVariableInteger;
import ql.model.expression.variable.ExpressionVariableUndefined;

public class CheckboxWidget extends CheckBox implements WidgetInterface {

    private final Question question;

    public CheckboxWidget(Question question) {
        this.question = question;
        this.managedProperty().bind(this.visibleProperty());
    }

    @Override
    public Expression getExpression() {
        try{
            return new ExpressionVariableBoolean(null, this.isSelected());
        } catch(IllegalArgumentException e){
            return new ExpressionVariableUndefined(null, ReturnType.BOOLEAN);
        }
    }

    @Override
    public void setExpression(String value) {
        this.setSelected(Boolean.valueOf(value));
    }

    @Override
    public void addComputedListener(SymbolTable symbolTable, ExpressionEvaluator expressionEvaluator) {
        symbolTable.addListener(e -> {
            Value value = expressionEvaluator.visit(symbolTable.getExpression(question.name));
            String text = value.isUndefined() ? "" : value.getBooleanValue().toString();
            this.setExpression(text);
        });
    }

    @Override
    public void addNonComputedListener(SymbolTable symbolTable) {
        this.selectedProperty().addListener(e -> {
            symbolTable.setExpression(question.name, getExpression(this, question.type));
        });
    }

    @Override
    public void setColor(String color) {
        this.setStyle(this.getStyle() + "-fx-text-inner-color: " + color + ";");
    }

    @Override
    public void setFont(String font) {
        // Checkbox has no text, so no action
    }

    @Override
    public void setFontSize(int fontSize) {
        // Checkbox has no text, so no action
    }

    @Override
    public void setWidth(int width) {
        this.setStyle(this.getStyle() + "-fx-font-size: " + width + ";");
    }
}
