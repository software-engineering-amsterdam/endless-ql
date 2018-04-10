package QL.Analysis;

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

public interface ExpressionVisitorInterface<T> {
    T visit(Expression expression);

    // binary expressions
    T visit(AdditionExpression expression);
    T visit(AndExpression expression);
    T visit(DivisionExpression expression);
    T visit(EqualExpression expression);
    T visit(GreaterOrEqualExpression expression);
    T visit(GreaterThanExpression expression);
    T visit(LessOrEqualExpression expression);
    T visit(LessThanExpression expression);
    T visit(MultiplicationExpression expression);
    T visit(NotEqualExpression expression);
    T visit(OrExpression expression);
    T visit(SubtractExpression expression);

    // unary expressions
    T visit(NegationExpression expression);
    T visit(NotExpression expression);

    // expression identifier
    T visit(IdentifierExpression expression);

    // expression constants
    T visit(BooleanConstant expression);
    T visit(DateConstant expression);
    T visit(DecimalConstant expression);
    T visit(IntegerConstant expression);
    T visit(MoneyConstant expression);
    T visit(StringConstant expression);
    T visit(UndefinedConstant expression);
}
