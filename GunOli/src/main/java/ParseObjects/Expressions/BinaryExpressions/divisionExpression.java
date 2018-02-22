package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.BinaryExpression;
import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;


public class DivisionExpression extends BinaryExpression<Double> {
    public DivisionExpression(String operator, Expression left, Expression right){
        super(operator,left,right);
    }

    @Override
    public EvaluationType returnType() {return EvaluationType.Decimal;}

    @Override
    public Constant<Double> evaluate() {return null;}

    @Override
    public Boolean isArithmetic(){
        return true;
    }
}
