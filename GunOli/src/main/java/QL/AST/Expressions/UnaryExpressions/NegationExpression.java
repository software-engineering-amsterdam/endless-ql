package QL.AST.Expressions.UnaryExpressions;

import QL.Analysis.ExpressionVisitorInterface;
import QL.AST.Expressions.Constant;
import QL.Analysis.EvaluationType;
import QL.AST.Expressions.Expression;
import QL.AST.Expressions.ExpressionConstants.DecimalConstant;
import QL.AST.Expressions.ExpressionConstants.IntegerConstant;
import QL.AST.Expressions.ExpressionConstants.UndefinedConstant;
import QL.AST.Expressions.UnaryExpression;

public class NegationExpression extends UnaryExpression {
    public NegationExpression(Expression expr, int line){
        super("-", expr, line);
    }

    @Override
    public EvaluationType returnType(){
        return EvaluationType.Decimal;
    }

    @Override
    public Constant evaluate(){
        if(!this.getExpression().evaluate().isArithmetic()){
            return new UndefinedConstant(this.getLineNumber());
        }

        if(this.getExpression().returnType() == EvaluationType.Integer){
            Integer exprValue = Integer.parseInt(this.getExpression().evaluate().getValue().toString());
            return new IntegerConstant(exprValue * -1, this.getLineNumber());
        }

        Double exprValue = Double.parseDouble(this.getExpression().evaluate().getValue().toString());
        return new DecimalConstant(exprValue * -1, this.getLineNumber());
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}
