package gui.widgets;

import javafx.scene.Node;
import javafx.scene.control.Slider;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableDecimal;

public abstract class SliderWidget extends Slider implements WidgetInterface {

    public final Question question;
    public SliderWidget(Question question, double min, double max, double step) {
        this.question = question;
        this.setMin(min);
        this.setMax(max);
        this.setBlockIncrement(step);
        this.setShowTickLabels(true);
        this.setShowTickMarks(true);
        this.setMajorTickUnit(step);
        this.setMinorTickCount(5);
        this.setBlockIncrement(10);
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
            Value value = expressionEvaluator.visit(symbolTable.getExpression(question.identifier));
            String text = value.isUndefined() ? "" : value.getDecimalValue().toString();
            this.setExpression(text);
        });
    }

    @Override
    public void addNonComputedListener(SymbolTable symbolTable) {
        this.valueProperty().addListener(e -> {
            symbolTable.setExpression(question.identifier, getExpression(this, question.type));
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

    @Override
    public Node getNode() {
        return this;
    }
}
