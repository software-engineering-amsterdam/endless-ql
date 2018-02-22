package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.BinaryExpression;
import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;

public class GreaterThanExpression extends BinaryExpression<Boolean> {

    public GreaterThanExpression(Expression left, Expression right){
        super("<", left, right);
    }

    @Override
    public EvaluationType returnType() {
        return EvaluationType.Boolean;
    }

    @Override
    public Constant<Boolean> evaluate() {
        Constant left = this.getExprLeft().evaluate();
        Constant right = this.getExprRight().evaluate();
        return left.greaterThan(right);
    }
    @Override
    public Boolean isArithmetic(){return false;}

    @Override
    public Boolean isLogical(){return true;}
}
