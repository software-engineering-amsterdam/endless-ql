package QL.AST.Expressions.BinaryExpressions;

import QL.Analysis.ExpressionVisitorInterface;
import QL.AST.Expressions.*;

public class OrExpression extends BinaryExpression {
    public OrExpression(Expression left, Expression right, int line){
        super("||", left, right, line);
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}
