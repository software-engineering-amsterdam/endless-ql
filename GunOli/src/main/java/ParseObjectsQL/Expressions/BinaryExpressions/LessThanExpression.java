package ParseObjectsQL.Expressions.BinaryExpressions;

import ParseObjectsQL.Expressions.ExpressionConstants.Constant;
import ParseObjectsQL.Expressions.EvaluationType;
import ParseObjectsQL.Expressions.Expression;
import ParseObjectsQL.Expressions.ExpressionConstants.BooleanConstant;
import ParseObjectsQL.Expressions.ExpressionConstants.UndefinedConstant;


public class LessThanExpression extends BinaryExpression {
    public LessThanExpression(Expression left, Expression right){
        super("<", left,right);
    }

    @Override
    public EvaluationType returnType() {
        return EvaluationType.Boolean;
    }

    @Override
    public Constant evaluate() {
        Expression rightExpr = this.getExprRight();
        Expression leftExpr = this.getExprLeft();

        if(!rightExpr.evaluate().isArithmetic() || !leftExpr.evaluate().isArithmetic()){
            return new UndefinedConstant();
        }

        Double left = Double.parseDouble(this.getExprLeft().evaluate().getValue().toString());
        Double right = Double.parseDouble(this.getExprRight().evaluate().getValue().toString());
        return new BooleanConstant(left < right);
    }
    @Override
    public Boolean isLogical(){
        return true;
    }
}
