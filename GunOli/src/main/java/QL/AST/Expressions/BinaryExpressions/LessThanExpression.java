package QL.AST.Expressions.BinaryExpressions;

import QL.Analysis.ExpressionVisitorInterface;
import QL.AST.Expressions.BinaryExpression;
import QL.AST.Expressions.Expression;

public class LessThanExpression extends BinaryExpression {
    public LessThanExpression(Expression left, Expression right, int line){
        super("<", left,right, line);
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}
