package ParseObjectsQL.Expressions.UnaryExpressions;

import ParseObjectsQL.Expressions.ExpressionConstants.Constant;
import ParseObjectsQL.Expressions.EvaluationType;
import ParseObjectsQL.Expressions.Expression;
import ParseObjectsQL.Expressions.ExpressionConstants.BooleanConstant;
import ParseObjectsQL.Expressions.ExpressionConstants.UndefinedConstant;

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
