package QL.AST.Expressions.UnaryExpressions;

import QL.Analysis.ExpressionVisitorInterface;
import QL.AST.Expressions.Constant;
import QL.Analysis.EvaluationType;
import QL.AST.Expressions.Expression;
import QL.AST.Expressions.ExpressionConstants.BooleanConstant;
import QL.AST.Expressions.ExpressionConstants.UndefinedConstant;
import QL.AST.Expressions.UnaryExpression;

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
            return new UndefinedConstant(this.getLineNumber());
        }

        BooleanConstant expr = (BooleanConstant) this.getExpression().evaluate();
        return new BooleanConstant(!expr.getValue(), this.getLineNumber());
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}
