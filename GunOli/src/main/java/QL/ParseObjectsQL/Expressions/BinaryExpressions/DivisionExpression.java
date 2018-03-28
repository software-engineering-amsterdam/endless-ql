package QL.ParseObjectsQL.Expressions.BinaryExpressions;

import QL.Analysis.ExpressionVisitorInterface;
import QL.ParseObjectsQL.Expressions.BinaryExpression;
import QL.ParseObjectsQL.Expressions.Constant;
import QL.Analysis.EvaluationType;
import QL.ParseObjectsQL.Expressions.Expression;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.DecimalConstant;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.IntegerConstant;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.UndefinedConstant;

public class DivisionExpression extends BinaryExpression {
    public DivisionExpression(Expression left, Expression right, int line){
        super("/", left, right, line);
    }

    @Override
    public EvaluationType returnType() {return EvaluationType.Decimal;}

    @Override
    public Constant evaluate(){
        Expression rightExpr = this.getExprRight();
        Expression leftExpr = this.getExprLeft();

        if(!rightExpr.evaluate().isArithmetic() || !leftExpr.evaluate().isArithmetic()){
            return new UndefinedConstant(this.getLineNumber());
        }

        if(leftExpr.returnType().equals(rightExpr.returnType())
                && rightExpr.returnType() == EvaluationType.Integer){
            Integer left = Integer.parseInt(leftExpr.evaluate().getValue().toString());
            Integer right = Integer.parseInt(rightExpr.evaluate().getValue().toString());

            return new IntegerConstant(left / right, this.getLineNumber());
        }
        Double left = Double.parseDouble(leftExpr.evaluate().getValue().toString());
        Double right = Double.parseDouble(rightExpr.evaluate().getValue().toString());

        return new DecimalConstant(left / right, this.getLineNumber());
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }

    @Override
    public Boolean isArithmetic() { return true; }
}