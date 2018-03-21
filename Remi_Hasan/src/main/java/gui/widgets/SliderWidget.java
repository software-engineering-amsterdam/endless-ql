package gui.widgets;

import javafx.scene.control.Slider;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableDecimal;

public abstract class SliderWidget extends Slider implements WidgetInterface{

    public final Question question;
    private final double min;
    private final double max;
    private final double step;

    public SliderWidget(Question question, double min, double max, double step) {
        this.question = question;
        this.min = min;
        this.max = max;
        this.step = step;
    }

    @Override
    public Expression getExpression() {
        return new ExpressionVariableDecimal(null, this.getValue());
    }

    @Override
    public void setExpression(String value) {
        this.setValue(Double.parseDouble(value));
    }

    @Override
    public void addComputedListener(SymbolTable symbolTable, ExpressionEvaluator expressionEvaluator) {
        symbolTable.addListener(e -> {
            Value value = expressionEvaluator.visit(symbolTable.getExpression(question.name));
            String text = value.isUndefined() ? "" : value.getDecimalValue().toString();
            this.setExpression(text);
        });
    }

    @Override
    public void addNonComputedListener(SymbolTable symbolTable) {
        this.valueProperty().addListener(e -> {
            symbolTable.setExpression(question.name, getExpression(this, question.type));
        });
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
