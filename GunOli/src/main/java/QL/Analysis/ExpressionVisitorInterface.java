package QL.Analysis;

import QL.AST.Expressions.BinaryExpressions.*;
import QL.AST.Expressions.IdentifierExpression;
import QL.AST.Expressions.Expression;
import QL.AST.Expressions.ExpressionConstants.*;
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
