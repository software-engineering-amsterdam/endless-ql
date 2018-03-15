package QL.ParseObjectsQL.Expressions.BinaryExpressions;

import QL.ParseObjectsQL.Expressions.ExpressionConstants.Constant;
import QL.ParseObjectsQL.Expressions.Expression;
import QL.ParseObjectsQL.Expressions.EvaluationType;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.BooleanConstant;

public class EqualExpression extends BinaryExpression {

    public EqualExpression(Expression left, Expression right){
        super("==", left, right);
    }

    @Override
    public EvaluationType returnType() {
        return EvaluationType.Boolean;
    }

    @Override
    public Constant evaluate() {
        Constant left = this.getExprLeft().evaluate();
        Constant right = this.getExprRight().evaluate();
        return new BooleanConstant(left.getValue().equals(right.getValue()));
    }

    @Override
    public Boolean isLogical(){return true;}
}
