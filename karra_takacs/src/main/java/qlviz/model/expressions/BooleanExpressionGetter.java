package qlviz.model.expressions;

import qlviz.model.expressions.booleanExpressions.BooleanExpression;
import qlviz.model.expressions.numericExpressions.NumericExpression;

public class BooleanExpressionGetter implements TypedExpressionVisitor<BooleanExpression> {

    @Override
    public BooleanExpression visit(NumericExpression numericExpression) {
       return null;
    }

    @Override
    public BooleanExpression visit(BooleanExpression expression) {
        return expression;
    }
}
