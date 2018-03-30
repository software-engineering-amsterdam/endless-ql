package qlviz.model.expressions;

import qlviz.model.expressions.booleanExpressions.BooleanExpression;
import qlviz.model.expressions.numericExpressions.NumericExpression;

public interface TypedExpressionVisitor<T> {
    T visit(NumericExpression numericExpression);
    T visit(BooleanExpression expression);
}
