package QL.ParseObjectsQL.Expressions.UnaryExpressions;

import QL.ParseObjectsQL.Expressions.ExpressionConstants.Constant;
import QL.ParseObjectsQL.Expressions.EvaluationType;
import QL.ParseObjectsQL.Expressions.Expression;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.BooleanConstant;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.UndefinedConstant;

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
