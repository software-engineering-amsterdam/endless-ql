package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.BinaryExpression;
import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;

public class EqualExpression extends BinaryExpression<Boolean> {

    public EqualExpression(Expression left, Expression right){
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
        return left.EqualTo(right);
    }
    @Override
    public  Boolean isArithmetic(){return false;}

    @Override
    public Boolean isLogical(){return true;}
}
