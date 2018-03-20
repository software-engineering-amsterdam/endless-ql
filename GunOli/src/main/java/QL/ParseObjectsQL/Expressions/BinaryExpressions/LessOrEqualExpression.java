package QL.ParseObjectsQL.Expressions.BinaryExpressions;

import QL.ParseObjectsQL.Expressions.ExpressionConstants.Constant;
import QL.ParseObjectsQL.Expressions.EvaluationType;
import QL.ParseObjectsQL.Expressions.Expression;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.BooleanConstant;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.UndefinedConstant;


public class LessOrEqualExpression extends BinaryExpression {

    public LessOrEqualExpression(Expression left, Expression right){
        super("<=", left, right);
    }

    @Override
    public EvaluationType returnType() {
        return EvaluationType.Boolean;
    }

    @Override
    public Constant evaluate() {
        Expression rightExpr = this.getExprRight();
        Expression leftExpr = this.getExprLeft();

        if(!rightExpr.evaluate().isArithmetic() || !leftExpr.evaluate().isArithmetic()){
            return new UndefinedConstant();
        }

        Double left = Double.parseDouble(this.getExprLeft().evaluate().getValue().toString());
        Double right = Double.parseDouble(this.getExprRight().evaluate().getValue().toString());
        return new BooleanConstant(left <= right);
    }

    @Override
    public Boolean isLogical() {return true;}
}
