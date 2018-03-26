package QL.ParseObjectsQL.Expressions.UnaryExpressions;

import QL.Analysis.ExpressionVisitorInterface;
import QL.ParseObjectsQL.Expressions.Constant;
import QL.ParseObjectsQL.Expressions.EvaluationType;
import QL.ParseObjectsQL.Expressions.Expression;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.DecimalConstant;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.IntegerConstant;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.UndefinedConstant;
import QL.ParseObjectsQL.Expressions.UnaryExpression;

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
            return new UndefinedConstant(this.getLine());
        }

        if(this.getExpression().returnType() == EvaluationType.Integer){
            Integer exprValue = Integer.parseInt(this.getExpression().evaluate().getValue().toString());
            return new IntegerConstant(exprValue * -1, this.getLine());
        }

        Double exprValue = Double.parseDouble(this.getExpression().evaluate().getValue().toString());
        return new DecimalConstant(exprValue * -1, this.getLine());
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }
}
