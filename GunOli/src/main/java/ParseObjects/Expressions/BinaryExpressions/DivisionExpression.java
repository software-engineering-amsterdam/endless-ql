package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.Constant;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;
import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import ParseObjects.Expressions.ExpressionConstants.IntegerConstant;

public class DivisionExpression extends BinaryExpression {
    public DivisionExpression(Expression left, Expression right){
        super("/", left, right);
    }

    @Override
    public EvaluationType returnType() {return EvaluationType.Decimal;}

    @Override
    public Constant evaluate(){
        if(this.getExprRight().returnType().equals(this.getExprRight().returnType())
                && this.getExprRight().returnType() == EvaluationType.Integer){
            Integer left = Integer.parseInt(this.getExprLeft().evaluate().getValue().toString());
            Integer right = Integer.parseInt(this.getExprRight().evaluate().getValue().toString());

            return new IntegerConstant(left / right);
        }
        Double left = Double.parseDouble(this.getExprLeft().evaluate().getValue().toString());
        Double right = Double.parseDouble(this.getExprRight().evaluate().getValue().toString());

        return new DecimalConstant(left / right);
    }

    @Override
    public Boolean isArithmetic() { return true; }
}