package QL.Analysis;

import QL.ParseObjectsQL.Expressions.BinaryExpressions.*;
import QL.ParseObjectsQL.Expressions.ConstantExpression;
import QL.ParseObjectsQL.Expressions.Expression;
import QL.ParseObjectsQL.Expressions.ExpressionConstants.*;
import QL.ParseObjectsQL.Expressions.UnaryExpressions.NegationExpression;
import QL.ParseObjectsQL.Expressions.UnaryExpressions.NotExpression;

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
    T visit(ConstantExpression expression);

    // expression constants
    T visit(BooleanConstant expression);
    T visit(DateConstant expression);
    T visit(DecimalConstant expression);
    T visit(IntegerConstant expression);
    T visit(MoneyConstant expression);
    T visit(StringConstant expression);
    T visit(UndefinedConstant expression);
}
