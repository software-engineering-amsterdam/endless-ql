package qlviz.model.expressions;

import qlviz.model.expressions.booleanExpressions.BooleanExpression;
import qlviz.model.expressions.numericExpressions.NumericExpression;

public interface ExpressionVisitor {
    void visit(NumericExpression numericExpression);
    void visit(BooleanExpression booleanExpression);
}
