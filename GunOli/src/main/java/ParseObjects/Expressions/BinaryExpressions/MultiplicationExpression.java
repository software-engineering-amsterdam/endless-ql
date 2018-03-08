package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.Constant;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;
import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import ParseObjects.Expressions.ExpressionConstants.IntegerConstant;
import ParseObjects.Expressions.ExpressionConstants.UndefinedConstant;

public class MultiplicationExpression extends BinaryExpression {
    public MultiplicationExpression(Expression left, Expression right){
        super("*", left, right);
    }

    @Override
    public EvaluationType returnType() {return EvaluationType.Decimal;}

    @Override
    public Constant evaluate(){
        Expression rightExpr = this.getExprRight();
        Expression leftExpr = this.getExprLeft();

        if(!rightExpr.evaluate().isArithmetic() || !leftExpr.evaluate().isArithmetic()){
            return new UndefinedConstant();
        }

        if(leftExpr.returnType().equals(rightExpr.returnType())
                && rightExpr.returnType() == EvaluationType.Integer){
            Integer left = Integer.parseInt(leftExpr.evaluate().getValue().toString());
            Integer right = Integer.parseInt(rightExpr.evaluate().getValue().toString());

            return new IntegerConstant(left * right);
        }
        Double left = Double.parseDouble(leftExpr.evaluate().getValue().toString());
        Double right = Double.parseDouble(rightExpr.evaluate().getValue().toString());

        return new DecimalConstant(left * right);
    }

    @Override
    public Boolean isArithmetic() { return true; }
}
