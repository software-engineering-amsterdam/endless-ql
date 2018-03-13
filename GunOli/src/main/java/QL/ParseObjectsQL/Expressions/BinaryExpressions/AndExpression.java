package QL.ParseObjectsQL.Expressions.BinaryExpressions;

import QL.ParseObjectsQL.Expressions.*;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.BooleanConstant;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.Constant;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.UndefinedConstant;

public class AndExpression extends BinaryExpression {
    public AndExpression(Expression left, Expression right){
        super("&&", left, right);
    }

    @Override
    public EvaluationType returnType() {
        return EvaluationType.Boolean;
    }

    @Override
    public Constant evaluate() {
        Expression rightExpr = this.getExprRight();
        Expression leftExpr = this.getExprLeft();

        if(!rightExpr.evaluate().isLogical() || !leftExpr.evaluate().isLogical()){
            return new UndefinedConstant();
        }

        BooleanConstant left = (BooleanConstant) this.getExprLeft().evaluate();
        BooleanConstant right = (BooleanConstant) this.getExprRight().evaluate();
        return new BooleanConstant(left.getValue() && right.getValue());
    }

    @Override
    public Boolean isLogical(){return true;}
}
