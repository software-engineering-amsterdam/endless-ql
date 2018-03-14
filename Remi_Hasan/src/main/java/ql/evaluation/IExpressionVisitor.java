package ql.evaluation;

import ql.model.expression.Expression;
import ql.model.expression.ExpressionIdentifier;
import ql.model.expression.binary.*;
import ql.model.expression.unary.ExpressionUnaryNeg;
import ql.model.expression.unary.ExpressionUnaryNot;
import ql.model.expression.variable.*;

import java.util.List;

public interface IExpressionVisitor<T> {
    T visit(Expression expression, List<Binding> bindings);

    T visit(ExpressionArithmeticDivide expression, List<Binding> bindings);

    T visit(ExpressionArithmeticMultiply expression, List<Binding> bindings);

    T visit(ExpressionArithmeticSubtract expression, List<Binding> bindings);

    T visit(ExpressionArithmeticSum expression, List<Binding> bindings);

    T visit(ExpressionComparisonEq expression, List<Binding> bindings);

    T visit(ExpressionComparisonGE expression, List<Binding> bindings);

    T visit(ExpressionComparisonGT expression, List<Binding> bindings);

    T visit(ExpressionComparisonLE expression, List<Binding> bindings);

    T visit(ExpressionComparisonLT expression, List<Binding> bindings);

    T visit(ExpressionLogicalAnd expression, List<Binding> bindings);

    T visit(ExpressionLogicalOr expression, List<Binding> bindings);

    T visit(ExpressionUnaryNot expression, List<Binding> bindings);

    T visit(ExpressionUnaryNeg expression, List<Binding> bindings);

    T visit(ExpressionVariableBoolean expression, List<Binding> bindings);

    T visit(ExpressionVariableDate expression, List<Binding> bindings);

    T visit(ExpressionVariableInteger expression, List<Binding> bindings);

    T visit(ExpressionVariableDecimal expression, List<Binding> bindings);

    T visit(ExpressionVariableMoney expression, List<Binding> bindings);

    T visit(ExpressionVariableString expression, List<Binding> bindings);

    T visit(ExpressionVariableUndefined expression, List<Binding> bindings);

    T visit(ExpressionIdentifier expression, List<Binding> bindings);
}

