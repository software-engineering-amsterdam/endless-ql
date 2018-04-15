package QL.AST.Expressions.BinaryExpressions;

import QL.Visitors.ExpressionVisitorInterface;
import QL.AST.Expressions.BinaryExpression;
import QL.AST.Expressions.Expression;

public class GreaterOrEqualExpression extends BinaryExpression {
    public GreaterOrEqualExpression(Expression left, Expression right, int line){
        super(">=", left, right, line);
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}
