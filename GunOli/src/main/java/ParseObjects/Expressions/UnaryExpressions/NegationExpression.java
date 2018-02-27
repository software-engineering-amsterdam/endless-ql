package ParseObjects.Expressions.UnaryExpressions;

import ParseObjects.Expressions.Constant;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;
import ParseObjects.Expressions.ExpressionConstants.DecimalConstant;
import ParseObjects.Expressions.UnaryExpression;

public class NegationExpression extends UnaryExpression<Double> {
    public NegationExpression(Expression<Double> expr){
        super("-", expr);
    }

    @Override
    public EvaluationType returnType(){
        return EvaluationType.Decimal;
    }

    @Override
    public Constant<Double> evaluate(){
        DecimalConstant expr = (DecimalConstant) this.getExpression().evaluate();
        return new DecimalConstant(expr.getValue() * -1);
    }
}
