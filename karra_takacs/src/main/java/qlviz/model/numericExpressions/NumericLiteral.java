package qlviz.model.numericExpressions;

import qlviz.interpreter.linker.NumericExpressionVisitor;

import java.math.BigDecimal;

public class NumericLiteral extends NumericExpression {

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
}
