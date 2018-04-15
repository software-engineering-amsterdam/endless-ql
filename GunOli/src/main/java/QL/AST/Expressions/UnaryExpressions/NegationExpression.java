package QL.AST.Expressions.UnaryExpressions;

import QL.Visitors.ExpressionVisitorInterface;
import QL.AST.Expressions.Expression;
import QL.AST.Expressions.UnaryExpression;

public class NegationExpression extends UnaryExpression {
    public NegationExpression(Expression expr, int line){
        super("-", expr, line);
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}
