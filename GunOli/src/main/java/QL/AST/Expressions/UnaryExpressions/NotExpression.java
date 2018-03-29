package QL.AST.Expressions.UnaryExpressions;

import QL.Analysis.ExpressionVisitorInterface;
import QL.AST.Expressions.Expression;
import QL.AST.Expressions.UnaryExpression;

public class NotExpression extends UnaryExpression {
    public NotExpression(Expression expr, int line){
        super("!", expr, line);
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}
