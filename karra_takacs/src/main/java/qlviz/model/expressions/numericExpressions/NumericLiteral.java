package qlviz.model.expressions.numericExpressions;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.interpreter.linker.NumericExpressionVisitor;
import qlviz.interpreter.linker.TypedNumericExpressionVisitor;
import qlviz.model.Node;
import qlviz.model.expressions.ExpressionVisitor;
import qlviz.model.expressions.TypedExpressionVisitor;

import java.math.BigDecimal;

public class NumericLiteral extends Node implements NumericExpression {
    private final BigDecimal value;

    public NumericLiteral(BigDecimal value, ParserRuleContext context) {
        super(context);
        this.value = value;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    @Override
    public void accept(NumericExpressionVisitor numericExpressionVisitor) {
        numericExpressionVisitor.visit(this);
    }

    @Override
    public <T> T accept(TypedNumericExpressionVisitor<T> numericExpressionVisitor) {
        return numericExpressionVisitor.visit(this);
    }

    @Override
    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public <T> T accept(TypedExpressionVisitor<T> typedExpressionVisitor) {
        return typedExpressionVisitor.visit(this);
    }
}
