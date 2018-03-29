package ql.visitors;

import ql.ast.expressions.binary.*;
import ql.ast.expressions.Identifier;
import ql.ast.expressions.literals.BooleanLiteral;
import ql.ast.expressions.literals.IntegerLiteral;
import ql.ast.expressions.literals.StringLiteral;

public interface ExpressionVisitor<T> {
    T visit(Addition addition);
    T visit(Division division);
    T visit(Equal equal);
    T visit(GreaterThan greaterThan);
    T visit(GreaterThanOrEqual greaterThanOrEqual);
    T visit(LessThan lessThan);
    T visit(LessThanOrEqual lessThanOrEqual);
    T visit(LogicalAnd logicalAnd);
    T visit(LogicalOr logicalOr);
    T visit(Multiplication multiplication);
    T visit(NotEqual notEqual);
    T visit(Subtraction subtraction);
    T visit(Identifier identifier);
    T visit(BooleanLiteral booleanLiteral);
    T visit(IntegerLiteral integerLiteral);
    T visit(StringLiteral stringLiteral);
}
