package QL.ParseObjectsQL.Expressions.UnaryExpressions;

import QL.Analysis.ExpressionVisitorInterface;
import QL.ParseObjectsQL.Expressions.Constant;
import QL.ParseObjectsQL.Expressions.EvaluationType;
import QL.ParseObjectsQL.Expressions.Expression;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.BooleanConstant;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.UndefinedConstant;
import QL.ParseObjectsQL.Expressions.UnaryExpression;

public class NotExpression extends UnaryExpression {
    public NotExpression(Expression expr, int line){
        super("!", expr, line);
    }

    @Override
    public EvaluationType returnType(){
        return EvaluationType.Boolean;
    }

    @Override
    public Constant evaluate(){
        if(!this.getExpression().evaluate().isLogical()){
            return new UndefinedConstant(this.getLine());
        }

        BooleanConstant expr = (BooleanConstant) this.getExpression().evaluate();
        return new BooleanConstant(!expr.getValue(), this.getLine());
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}
