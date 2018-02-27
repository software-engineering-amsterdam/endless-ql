package qlviz.model.numericExpressions;

import qlviz.interpreter.linker.NumericExpressionVisitor;

import java.math.BigDecimal;

public class NumericNegation  extends NumericExpression {

    private final NumericExpression innerExpression;

    public NumericNegation(NumericExpression innerExpression) {
        this.innerExpression = innerExpression;
    }

    @Override
    public BigDecimal evaluate() {
        return this.innerExpression.evaluate().multiply(BigDecimal.valueOf(-1));
    }

    @Override
    public void accept(NumericExpressionVisitor numericExpressionVisitor) {
        numericExpressionVisitor.visit(this);
    }

    public NumericExpression getInnerExpression() {
        return innerExpression;
    }
}
