package ParseObjects.Expressions;

import java.beans.Expression;

public class BinaryExpressionPlus extends BinaryExpression {

    public BinaryExpressionPlus(String operator, Expression left, Expression right ){
        super(left, right, operator);

    }

    @Override
    public EvaluationType returnType() {
        return EvaluationType.Integer;
    }

    @Override
    public Object evaluate() {
        //ToDo: Implement
        return null;
    }
}
