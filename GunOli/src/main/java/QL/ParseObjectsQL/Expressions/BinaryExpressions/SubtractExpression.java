package QL.ParseObjectsQL.Expressions.BinaryExpressions;

import QL.ParseObjectsQL.Expressions.ExpressionConstants.Constant;
import QL.ParseObjectsQL.Expressions.EvaluationType;
import QL.ParseObjectsQL.Expressions.Expression;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.DecimalConstant;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.IntegerConstant;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.UndefinedConstant;

public class SubtractExpression extends BinaryExpression {

    public SubtractExpression(Expression left, Expression right) { super("-", left, right); }


    @Override
    public EvaluationType returnType() {
        return EvaluationType.Decimal;
    }

    @Override
    public Constant evaluate() {
        Expression rightExpr = this.getExprRight();
        Expression leftExpr = this.getExprLeft();

        try {
            if (!rightExpr.evaluate().isArithmetic() || !leftExpr.evaluate().isArithmetic()) {
                throw new IllegalArgumentException("Not possible: non-numeric");
                //return new UndefinedConstant();
            }

            if (leftExpr.returnType().equals(rightExpr.returnType())
                    && rightExpr.returnType() == EvaluationType.Integer) {
                Integer left = Integer.parseInt(leftExpr.evaluate().getValue().toString());
                Integer right = Integer.parseInt(rightExpr.evaluate().getValue().toString());

                return new IntegerConstant(left - right);
            }
            Double left = Double.parseDouble(leftExpr.evaluate().getValue().toString());
            Double right = Double.parseDouble(rightExpr.evaluate().getValue().toString());

            return new DecimalConstant(left - right);
        } catch (Exception e){
            System.out.print(e);
            return new UndefinedConstant();
        }
    }

    @Override
    public Boolean isArithmetic(){
        return true;
    }
}
