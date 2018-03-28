package QL.AST.Expressions.BinaryExpressions;

import QL.Analysis.ExpressionVisitorInterface;
import QL.AST.Expressions.BinaryExpression;
import QL.AST.Expressions.Constant;
import QL.Analysis.EvaluationType;
import QL.AST.Expressions.Expression;
import QL.AST.Expressions.ExpressionConstants.BooleanConstant;
import QL.AST.Expressions.ExpressionConstants.UndefinedConstant;

public class GreaterOrEqualExpression extends BinaryExpression {
    public GreaterOrEqualExpression(Expression left, Expression right, int line){
        super(">=", left, right, line);
    }

    @Override
    public EvaluationType returnType() {
        return EvaluationType.Boolean;
    }

    @Override
    public Constant evaluate() {
        Expression rightExpr = this.getExprRight();
        Expression leftExpr = this.getExprLeft();

        if(!rightExpr.evaluate().isArithmetic() || !leftExpr.evaluate().isArithmetic()){
            return new UndefinedConstant(this.getLineNumber());
        }

        Double left = Double.parseDouble(this.getExprLeft().evaluate().getValue().toString());
        Double right = Double.parseDouble(this.getExprRight().evaluate().getValue().toString());
        return new BooleanConstant(left >= right, this.getLineNumber());
    }

    @Override
    public <T> T accept(ExpressionVisitorInterface<T> visitor){
        return visitor.visit(this);
    }

    @Override
    public Boolean isLogical(){return true;}
}
