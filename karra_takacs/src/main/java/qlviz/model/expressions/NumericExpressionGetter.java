package qlviz.model.expressions;

import qlviz.model.expressions.booleanExpressions.BooleanExpression;
import qlviz.model.expressions.numericExpressions.NumericExpression;

public class NumericExpressionGetter implements TypedExpressionVisitor<NumericExpression> {

    @Override
    public NumericExpression visit(NumericExpression numericExpression) {
        return numericExpression;
    }

    @Override
    public NumericExpression visit(BooleanExpression expression) {
        return null;
    }
}

