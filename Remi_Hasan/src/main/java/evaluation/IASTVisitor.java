package evaluation;

import model.expression.Expression;
import model.expression.ExpressionIdentifier;
import model.expression.binary.*;
import model.expression.unary.ExpressionUnaryNeg;
import model.expression.unary.ExpressionUnaryNot;
import model.expression.variable.*;

public interface IASTVisitor<T> {
    T visit(Expression e);

    T visit(ExpressionArithmeticDivide e);

    T visit(ExpressionArithmeticMultiply e);

    T visit(ExpressionArithmeticSubtract e);

    T visit(ExpressionArithmeticSum e);

    T visit(ExpressionComparisonEq e);

    T visit(ExpressionComparisonGE e);

    T visit(ExpressionComparisonGT e);

    T visit(ExpressionComparisonLE e);

    T visit(ExpressionComparisonLT e);

    T visit(ExpressionLogicalAnd e);

    T visit(ExpressionLogicalOr e);

    T visit(ExpressionUnaryNot e);

    T visit(ExpressionUnaryNeg e);

    T visit(ExpressionVariableBoolean e);

    T visit(ExpressionVariableDate e);

    T visit(ExpressionVariableInteger e);

    T visit(ExpressionVariableDecimal e);

    T visit(ExpressionVariableMoney e);

    T visit(ExpressionVariableString e);

    T visit(ExpressionVariableUndefined e);

    T visit(ExpressionIdentifier e);
}