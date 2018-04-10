package QL.Analysis;

import QL.AST.Expressions.BinaryExpression;
import QL.AST.Expressions.BinaryExpressions.AdditionExpression;
import QL.AST.Expressions.BinaryExpressions.AndExpression;
import QL.AST.Expressions.BinaryExpressions.DivisionExpression;
import QL.AST.Expressions.BinaryExpressions.EqualExpression;
import QL.AST.Expressions.BinaryExpressions.GreaterOrEqualExpression;
import QL.AST.Expressions.BinaryExpressions.GreaterThanExpression;
import QL.AST.Expressions.BinaryExpressions.LessOrEqualExpression;
import QL.AST.Expressions.BinaryExpressions.LessThanExpression;
import QL.AST.Expressions.BinaryExpressions.MultiplicationExpression;
import QL.AST.Expressions.BinaryExpressions.NotEqualExpression;
import QL.AST.Expressions.BinaryExpressions.OrExpression;
import QL.AST.Expressions.BinaryExpressions.SubtractExpression;
import QL.AST.Expressions.Expression;
import QL.AST.Expressions.ExpressionConstants.BooleanConstant;
import QL.AST.Expressions.ExpressionConstants.DateConstant;
import QL.AST.Expressions.ExpressionConstants.DecimalConstant;
import QL.AST.Expressions.ExpressionConstants.IntegerConstant;
import QL.AST.Expressions.ExpressionConstants.MoneyConstant;
import QL.AST.Expressions.ExpressionConstants.StringConstant;
import QL.AST.Expressions.ExpressionConstants.UndefinedConstant;
import QL.AST.Expressions.IdentifierExpression;
import QL.AST.Expressions.UnaryExpressions.NegationExpression;
import QL.AST.Expressions.UnaryExpressions.NotExpression;

public class GenericExpressionVisitor<T> implements ExpressionVisitorInterface<T>{
    private T visitBinaryExpression(BinaryExpression expression) {
        expression.getExprLeft().accept(this);
        expression.getExprRight().accept(this);
        return null;
    }

    @Override
    public T visit(Expression expression){
        return expression.accept(this);
    }

    // binary expressions
    @Override
    public T visit(AdditionExpression expression){ return this.visitBinaryExpression(expression); }
    public T visit(AndExpression expression){ return this.visitBinaryExpression(expression); }
    public T visit(DivisionExpression expression){ return this.visitBinaryExpression(expression); }
    public T visit(EqualExpression expression){ return this.visitBinaryExpression(expression); }
    public T visit(GreaterOrEqualExpression expression){ return this.visitBinaryExpression(expression); }
    public T visit(GreaterThanExpression expression){ return this.visitBinaryExpression(expression); }
    public T visit(LessOrEqualExpression expression){ return this.visitBinaryExpression(expression); }
    public T visit(LessThanExpression expression){ return this.visitBinaryExpression(expression); }
    public T visit(MultiplicationExpression expression){ return this.visitBinaryExpression(expression); }
    public T visit(NotEqualExpression expression){ return this.visitBinaryExpression(expression); }
    public T visit(OrExpression expression){ return this.visitBinaryExpression(expression); }
    public T visit(SubtractExpression expression){ return this.visitBinaryExpression(expression); }

    // unary expressions
    public T visit(NegationExpression expression){ return expression.getExpression().accept(this); }
    public T visit(NotExpression expression){ return expression.getExpression().accept(this); }

    // expression identifier
    public T visit(IdentifierExpression expression){ return null; }

    // expression constants
    public T visit(BooleanConstant expression){ return null; }
    public T visit(DateConstant expression){ return null; }
    public T visit(DecimalConstant expression){ return null; }
    public T visit(IntegerConstant expression){ return null; }
    public T visit(MoneyConstant expression){ return null; }
    public T visit(StringConstant expression){ return null; }
    public T visit(UndefinedConstant expression){ return null; }
}
