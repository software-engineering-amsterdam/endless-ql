package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.BinaryExpression;
import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.Expression;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.ExpressionConstants.BooleanConstant;

public class EqualExpression extends BinaryExpression<Boolean> {

    public EqualExpression(Expression left, Expression right){
        super("==", left, right);
    }

    @Override
    public EvaluationType returnType() {
        return EvaluationType.Boolean;
    }

    @Override
    public Constant<Boolean> evaluate() {
        Constant left = this.getExprLeft().evaluate();
        Constant right = this.getExprRight().evaluate();
        return new BooleanConstant(left.getValue().equals(right.getValue()));
    }

    @Override
    public Boolean isLogical(){return true;}
}
