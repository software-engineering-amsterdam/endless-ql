package qlviz.model.numericExpressions;

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
}
