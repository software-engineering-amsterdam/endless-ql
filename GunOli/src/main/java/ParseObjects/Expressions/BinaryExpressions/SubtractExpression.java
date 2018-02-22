package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.BinaryExpression;
import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;

public class SubtractExpression extends BinaryExpression<Double> {

    public SubtractExpression(Expression left, Expression right) { super("-", left, right); }


    @Override
    public EvaluationType returnType() {
        return EvaluationType.Decimal;
    }

    @Override
    public Constant<Double> evaluate() {
        Constant left = this.getExprLeft().evaluate();
        Constant right = this.getExprRight().evaluate();
        return left.subtract(right);
    }


    @Override
    public Boolean isArithmetic(){
        return true;
    }
}
