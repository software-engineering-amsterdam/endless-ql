package astvisitor;

import expression.binary.*;
import expression.unary.ExpressionUnaryNeg;
import expression.unary.ExpressionUnaryNot;
import expression.variable.*;

public class BaseASTVisitor<T> implements IASTVisitor<T>, IArithmeticValue {

    @Override
    public T visit(ExpressionArithmeticDivide e) {
        return this.visit(e);
    }

    @Override
    public T visit(ExpressionArithmeticMultiply e) {
        return this.visit(e);
    }

    @Override
    public T visit(ExpressionArithmeticSubtract e) {
        return this.visit(e);
    }

    @Override
    public T visit(ExpressionArithmeticSum e) {
        return this.visit(e);
    }

    @Override
    public T visit(ExpressionComparisonEq e) {
        return this.visit(e);
    }

    @Override
    public T visit(ExpressionComparisonGE e) {
        return this.visit(e);
    }

    @Override
    public T visit(ExpressionComparisonGT e) {
        return this.visit(e);
    }

    @Override
    public T visit(ExpressionComparisonLE e) {
        return this.visit(e);
    }

    @Override
    public T visit(ExpressionComparisonLT e) {
        return this.visit(e);
    }

    @Override
    public T visit(ExpressionLogicalAnd e) {
        return this.visit(e);
    }

    @Override
    public T visit(ExpressionLogicalOr e) {
        return this.visit(e);
    }

    @Override
    public T visit(ExpressionUnaryNot e) {
        return this.visit(e);
    }

    @Override
    public T visit(ExpressionUnaryNeg e) {
        return this.visit(e);
    }

    @Override
    public T visit(ExpressionVariableBoolean e) {
        return this.visit(e);
    }

    @Override
    public T visit(ExpressionVariableDate e) {
        return this.visit(e);
    }

    @Override
    public T visit(ExpressionVariableNumber e) {
        return this.visit(e);
    }

    @Override
    public T visit(ExpressionVariableString e) {
        return this.visit(e);
    }

    @Override
    public T visit(ExpressionVariableUndefined e) {
        return this.visit(e);
    }

    @Override
    public IntValue divide(IntValue left, IntValue right) {
        return new IntValue(left.value.intValue() / right.value.intValue());
    }
}
