package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.BinaryExpression;
import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;


public class LessThanExpression extends BinaryExpression<Boolean> {
    public LessThanExpression(Expression left, Expression right){
        super("<", left,right);
    }

    @Override
    public EvaluationType returnType() {
        Constant left = this.getExprLeft().evaluate();
        Constant right = this.getExprRight().evaluate();
        return left.lessThan(right);
    }

    @Override
    public Constant<Boolean> evaluate() {
        return null;
    }

    @Override
    public Boolean isArithmetic() {
        return false;
    }
    @Override
    public Boolean isLogical(){
        return true;
    }
}
