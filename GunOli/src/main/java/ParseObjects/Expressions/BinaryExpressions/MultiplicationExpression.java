package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.BinaryExpression;
import ParseObjects.Expressions.BinaryExpression;
import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;

public class MultiplicationExpression extends BinaryExpression<Double> {
    public MultiplicationExpression(String operator, Expression left, Expression right){

        super(operator, left, right);
    }

    @Override
    public EvaluationType returnType() {return EvaluationType.Decimal;}

    @Override
    public Constant<Double> evaluate() {
        Constant left = this.getExprLeft().evaluate();
        Constant right = this.getExprRight().evaluate();
        return left.multipl(right);
    }

    @Override
    public Boolean isArithmetic() {return true;}
}
