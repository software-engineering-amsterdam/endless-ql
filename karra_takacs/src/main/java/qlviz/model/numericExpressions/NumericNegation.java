package qlviz.model.numericExpressions;

import qlviz.interpreter.linker.NumericExpressionVisitor;
import qlviz.interpreter.linker.TypedNumericExpressionVisitor;

import java.math.BigDecimal;

public class NumericNegation  implements NumericExpression {

    private final NumericExpression innerExpression;

    public NumericNegation(NumericExpression innerExpression) {
        this.innerExpression = innerExpression;
    }


    @Override
    public void accept(NumericExpressionVisitor numericExpressionVisitor) {
        numericExpressionVisitor.visit(this);
    }

    @Override
    public <T> T accept(TypedNumericExpressionVisitor<T> numericExpressionVisitor) {
        return numericExpressionVisitor.visit(this);
    }

    public NumericExpression getInnerExpression() {
        return innerExpression;
    }
}
