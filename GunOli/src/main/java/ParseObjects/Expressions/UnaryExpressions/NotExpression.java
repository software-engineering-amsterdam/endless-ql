package ParseObjects.Expressions.UnaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.Constant;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;
import ParseObjects.Expressions.ExpressionConstants.BooleanConstant;

public class NotExpression extends UnaryExpression<Boolean> {
    public NotExpression(Expression<Boolean> expr){
        super("!", expr);
    }

    @Override
    public EvaluationType returnType(){
        return EvaluationType.Boolean;
    }

    @Override
    public Constant<Boolean> evaluate(){
        BooleanConstant expr = (BooleanConstant) this.getExpression().evaluate();
        return new BooleanConstant(!expr.getValue());
    }
}
