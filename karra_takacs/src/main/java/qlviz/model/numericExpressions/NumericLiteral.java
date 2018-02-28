package qlviz.model.numericExpressions;

import qlviz.interpreter.linker.NumericExpressionVisitor;
import qlviz.interpreter.linker.TypedNumericExpressionVisitor;

import java.math.BigDecimal;

public class NumericLiteral implements NumericExpression {

    private final BigDecimal value;

    public NumericLiteral(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal evaluate() {
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
}
