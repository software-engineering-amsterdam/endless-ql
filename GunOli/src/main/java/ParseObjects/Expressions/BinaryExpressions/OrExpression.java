package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.*;
import ParseObjects.Expressions.ExpressionConstants.BooleanConstant;
import ParseObjects.Expressions.ExpressionConstants.Constant;
import ParseObjects.Expressions.ExpressionConstants.UndefinedConstant;

public class OrExpression extends BinaryExpression {

    public OrExpression(Expression left, Expression right){
        super("||", left, right);
    }

    @Override
    public EvaluationType returnType() {
        return EvaluationType.Boolean;
    }

    @Override
    public Constant evaluate() {
        Expression rightExpr = this.getExprRight();
        Expression leftExpr = this.getExprLeft();

        if(!rightExpr.evaluate().isLogical() || !leftExpr.evaluate().isLogical()){
            return new UndefinedConstant();
        }

        BooleanConstant left = (BooleanConstant) this.getExprLeft().evaluate();
        BooleanConstant right = (BooleanConstant) this.getExprRight().evaluate();
        return new BooleanConstant(left.getValue() || right.getValue());
    }

    @Override
    public Boolean isLogical(){return true;}
}
