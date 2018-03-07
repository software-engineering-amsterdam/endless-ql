package astvisitor;

import expression.binary.*;
import expression.unary.ExpressionUnaryNeg;
import expression.unary.ExpressionUnaryNot;
import expression.variable.*;

public class BaseASTVisitor<T> implements IASTVisitor {

    @Override
    public NumValue visit(ExpressionArithmeticDivide e) {
        return this.visit(e);
    }

    @Override
    public NumValue visit(ExpressionArithmeticMultiply e) {
        return this.visit(e);
    }

    @Override
    public NumValue visit(ExpressionArithmeticSubtract e) {
        return this.visit(e);
    }

    @Override
    public NumValue visit(ExpressionArithmeticSum e) {
        return this.visit(e);
    }

    @Override
    public BoolValue visit(ExpressionComparisonEq e) {
        return this.visit(e);
    }

    @Override
    public BoolValue visit(ExpressionComparisonGE e) {
        return this.visit(e);
    }

    @Override
    public BoolValue visit(ExpressionComparisonGT e) {
        return this.visit(e);
    }

    @Override
    public BoolValue visit(ExpressionComparisonLE e) {
        return this.visit(e);
    }

    @Override
    public BoolValue visit(ExpressionComparisonLT e) {
        return this.visit(e);
    }

    @Override
    public BoolValue visit(ExpressionLogicalAnd e) {
        return this.visit(e);
    }

    @Override
    public BoolValue visit(ExpressionLogicalOr e) {
        return this.visit(e);
    }

    @Override
    public BoolValue visit(ExpressionUnaryNot e) {
        return this.visit(e);
    }

    @Override
    public NumValue visit(ExpressionUnaryNeg e) {
        return this.visit(e);
    }

    @Override
    public BoolValue visit(ExpressionVariableBoolean e) {
        return this.visit(e);
    }

    @Override
    public DateValue visit(ExpressionVariableDate e) {
        return this.visit(e);
    }

    @Override
    public NumValue visit(ExpressionVariableNumber e) {
        return this.visit(e);
    }

    @Override
    public StringValue visit(ExpressionVariableString e) {
        return this.visit(e);
    }
}
