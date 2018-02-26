package qlviz.model.numericExpressions;

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
}
