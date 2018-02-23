package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.BinaryExpression;
import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;
import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;

public class SubtractExpression extends BinaryExpression<Double> {

    public SubtractExpression(Expression left, Expression right) { super("-", left, right); }


    @Override
    public EvaluationType returnType() {
        return EvaluationType.Decimal;
    }

    @Override
    public Constant<Double> evaluate() {
        DecimalConstant left  = (DecimalConstant) this.getExprLeft().evaluate();
        DecimalConstant right = (DecimalConstant) this.getExprRight().evaluate();
        return new DecimalConstant(left.getValue() - right.getValue());
    }

    @Override
    public Boolean isArithmetic(){
        return true;
    }
}
