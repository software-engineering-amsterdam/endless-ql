package ParseObjects.Expressions.UnaryExpressions;

import ParseObjects.Expressions.ExpressionConstants.Constant;
import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;
import ParseObjects.Expressions.ExpressionConstants.BooleanConstant;
import ParseObjects.Expressions.ExpressionConstants.UndefinedConstant;

public class NotExpression extends UnaryExpression {
    public NotExpression(Expression<Boolean> expr){
        super("!", expr);
    }

    @Override
    public EvaluationType returnType(){
        return EvaluationType.Boolean;
    }

    @Override
    public Constant evaluate(){
        if(!this.getExpression().isLogical()){
            return new UndefinedConstant();
        }

        BooleanConstant expr = (BooleanConstant) this.getExpression().evaluate();
        return new BooleanConstant(!expr.getValue());
    }

}
