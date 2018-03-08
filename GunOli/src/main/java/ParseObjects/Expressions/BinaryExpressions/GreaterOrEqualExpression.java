package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.Constant;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;
import ParseObjects.Expressions.ExpressionConstants.BooleanConstant;
import ParseObjects.Expressions.ExpressionConstants.UndefinedConstant;

public class GreaterOrEqualExpression extends BinaryExpression {
    public GreaterOrEqualExpression(Expression left, Expression right){
        super(">=", left, right);
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
        return new BooleanConstant(left >= right);
    }

    @Override
    public Boolean isLogical(){return true;}
}
