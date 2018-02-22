package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.BinaryExpression;
import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;
import ParseObjects.Expressions.ExpressionConstants.BooleanConstant;
import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;

public class GreaterOrEqualExpression extends BinaryExpression<Boolean> {
    public GreaterOrEqualExpression(Expression left, Expression right){
        super("<=", left, right);
    }

    @Override
    public EvaluationType returnType() {
        return EvaluationType.Boolean;
    }

    @Override
    public Constant<Boolean> evaluate() {
        DecimalConstant left = (DecimalConstant) this.getExprLeft().evaluate();
        DecimalConstant right = (DecimalConstant) this.getExprRight().evaluate();
        return new BooleanConstant(left.getValue() >= right.getValue());
    }

    @Override
    public Boolean isLogical(){return true;}
}
