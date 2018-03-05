package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.*;
import ParseObjects.Expressions.ExpressionConstants.BooleanConstant;
import ParseObjects.Expressions.ExpressionConstants.Constant;

public class OrExpression extends BinaryExpression<Boolean> {

    public OrExpression(Expression left, Expression right){
        super("||", left, right);
    }

    @Override
    public EvaluationType returnType() {
        return EvaluationType.Boolean;
    }

    @Override
    public Constant<Boolean> evaluate() {
        BooleanConstant left = (BooleanConstant) this.getExprLeft().evaluate();
        BooleanConstant right = (BooleanConstant) this.getExprRight().evaluate();
        return new BooleanConstant(left.getValue() || right.getValue());
    }

    @Override
    public Boolean isLogical(){return true;}
}
