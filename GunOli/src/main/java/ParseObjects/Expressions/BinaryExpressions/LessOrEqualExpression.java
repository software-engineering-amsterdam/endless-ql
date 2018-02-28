package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.BinaryExpression;
import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;
import ParseObjects.Expressions.ExpressionConstants.BooleanConstant;
import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;


public class LessOrEqualExpression extends BinaryExpression<Boolean> {

    public LessOrEqualExpression(Expression left, Expression right){
        super("<=", left, right);
    }

    @Override
    public EvaluationType returnType() {
        return EvaluationType.Boolean;
    }

    @Override
    public Constant<Boolean> evaluate() {
        Double left = Double.parseDouble(this.getExprLeft().evaluate().toString());
        Double right = Double.parseDouble(this.getExprRight().evaluate().toString());
        return new BooleanConstant(left <= right);
    }

    @Override
    public Boolean isLogical() {return true;}
}
