package QL.ParseObjectsQL.Expressions.BinaryExpressions;

import QL.Analysis.EvaluationType;
import QL.Analysis.ExpressionVisitorInterface;
import QL.ParseObjectsQL.Expressions.*;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.BooleanConstant;
import QL.ParseObjectsQL.Expressions.Constant;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.UndefinedConstant;

public class AndExpression extends BinaryExpression {
    public AndExpression(Expression left, Expression right, int line){
        super("&&", left, right, line);
    }

    @Override
    public EvaluationType returnType() {
        return EvaluationType.Boolean;
    }

    @Override
    public Constant evaluate() {
        Expression rightExpr = this.getExprRight();
        Expression leftExpr = this.getExprLeft();

        if(!rightExpr.evaluate().isLogical() || !leftExpr.evaluate().isLogical()){
            return new UndefinedConstant(this.getLineNumber());
        }

        BooleanConstant left = (BooleanConstant) this.getExprLeft().evaluate();
        BooleanConstant right = (BooleanConstant) this.getExprRight().evaluate();
        return new BooleanConstant(left.getValue() && right.getValue(), this.getLineNumber());
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }

    @Override
    public Boolean isLogical(){return true;}
}
