package ParseObjectsQL.Expressions.BinaryExpressions;

import ParseObjectsQL.Expressions.ExpressionConstants.Constant;
import ParseObjectsQL.Expressions.EvaluationType;
import ParseObjectsQL.Expressions.Expression;
import ParseObjectsQL.Expressions.ExpressionConstants.DecimalConstant;
import ParseObjectsQL.Expressions.ExpressionConstants.IntegerConstant;
import ParseObjectsQL.Expressions.ExpressionConstants.UndefinedConstant;

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
