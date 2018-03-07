package astvisitor;

import expression.binary.*;
import expression.unary.ExpressionUnaryNeg;
import expression.unary.ExpressionUnaryNot;
import expression.variable.*;

public class InterperterVisitor extends BaseASTVisitor {

    @Override
    public NumValue visit(ExpressionArithmeticDivide e) {
        Value leftInterpreted = e.left.accept(this);
        Value rightInterpreted = e.right.accept(this);
        return leftInterpreted.divide(rightInterpreted);
    }

    @Override
    public NumValue visit(ExpressionArithmeticMultiply e) {
        return null;
    }

    @Override
    public NumValue visit(ExpressionArithmeticSubtract e) {
        return null;
    }

    @Override
    public NumValue visit(ExpressionArithmeticSum e) {
        return null;
    }

    @Override
    public BoolValue visit(ExpressionComparisonEq e) {
        return null;
    }

    @Override
    public BoolValue visit(ExpressionComparisonGE e) {
        return null;
    }

    @Override
    public BoolValue visit(ExpressionComparisonGT e) {
        return null;
    }

    @Override
    public BoolValue visit(ExpressionComparisonLE e) {
        return null;
    }

    @Override
    public BoolValue visit(ExpressionComparisonLT e) {
        return null;
    }

    @Override
    public BoolValue visit(ExpressionLogicalAnd e) {
        return null;
    }

    @Override
    public BoolValue visit(ExpressionLogicalOr e) {
        return null;
    }

    @Override
    public BoolValue visit(ExpressionUnaryNot e) {
        return null;
    }

    @Override
    public NumValue visit(ExpressionUnaryNeg e) {
        return null;
    }

    @Override
    public BoolValue visit(ExpressionVariableBoolean e) {
        return null;
    }

    @Override
    public DateValue visit(ExpressionVariableDate e) {
        return null;
    }

    @Override
    public NumValue visit(ExpressionVariableNumber e) {
        return null;
    }

    @Override
    public StringValue visit(ExpressionVariableString e) {
        return null;
    }
}