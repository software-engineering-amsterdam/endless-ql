package main.org.uva.ql.visitor;

import main.org.uva.ql.ast.expression.binary.*;

public interface ExpressionVisitor<T, C> {

    T visit(Addition addition, C context);

    T visit(Division division, C context);

    T visit(Equal equal, C context);

    T visit(GreaterThan greaterThan, C context);

    T visit(GreaterThanEqualTo greaterThanEqualTo, C context);

    T visit(LessThan lessThan, C context);

    T visit(LessThanEqualTo lessThanEqualTo, C context);

    T visit(Multiplication multiplication, C context);

    T visit(NotEqual notEqual, C context);

    T visit(Subtraction subtraction, C context);
}
