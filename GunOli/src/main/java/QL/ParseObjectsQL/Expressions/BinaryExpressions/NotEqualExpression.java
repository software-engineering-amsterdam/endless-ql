package QL.ParseObjectsQL.Expressions.BinaryExpressions;

import QL.Analysis.ExpressionVisitorInterface;
import QL.ParseObjectsQL.Expressions.BinaryExpression;
import QL.ParseObjectsQL.Expressions.Constant;
import QL.Analysis.EvaluationType;
import QL.ParseObjectsQL.Expressions.Expression;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.BooleanConstant;

public class NotEqualExpression extends BinaryExpression {
    public NotEqualExpression(Expression left, Expression right, int line){
        super("!=",left, right, line);
    }

    @Override
    public EvaluationType returnType() {
        return EvaluationType.Boolean;
    }

    @Override
    public Constant evaluate() {
        Constant left = this.getExprLeft().evaluate();
        Constant right = this.getExprRight().evaluate();
        return new BooleanConstant(!left.getValue().equals(right.getValue()), this.getLineNumber());
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }

    @Override
    public Boolean isLogical(){return true;}
}
