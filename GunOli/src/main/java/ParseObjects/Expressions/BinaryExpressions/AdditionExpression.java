package ParseObjects.Expressions.BinaryExpressions;

import ParseObjects.Expressions.BinaryExpression;
import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;
import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import com.sun.tools.javac.comp.Todo;

public class AdditionExpression extends BinaryExpression<Double> {
    public AdditionExpression(Expression left, Expression right){
        super("+", left, right);
    }

    @Override
    public EvaluationType returnType(){
        return EvaluationType.Decimal;
    }

    @Override
    public Constant<Double> evaluate(){
        //Todo: Find a better way to do this
        Number left = Double.parseDouble(this.getExprLeft().evaluate().getValue().toString());
        Number right = Double.parseDouble(this.getExprRight().evaluate().getValue().toString());

        return new DecimalConstant(left.doubleValue() + right.doubleValue());
    }

    @Override
    public Boolean isArithmetic(){return true;}
}

