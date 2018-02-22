package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.BinaryExpression;
import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;


public class divisionExpression extends BinaryExpression<Double> {
    public divisionExpression(String operator, Expression left, Expression right){
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
