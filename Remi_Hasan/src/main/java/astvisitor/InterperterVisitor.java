package astvisitor;

import expression.binary.*;
import expression.unary.ExpressionUnaryNeg;
import expression.unary.ExpressionUnaryNot;
import expression.variable.*;

public class InterperterVisitor extends BaseASTVisitor<Value> {

    @Override
    public IntValue visit(ExpressionVariableNumber e) {
        return new IntValue(e.value);
    }

    @Override
    public Value visit(ExpressionVariableString e) {
        return new StringValue(e.value);
    }

    @Override
    public Value visit(ExpressionVariableUndefined e) {
        return null;
    }

    @Override
    public Value visit(ExpressionArithmeticDivide e) {
        Value leftInterpreted = e.left.accept(this);
        Value rightInterpreted = e.right.accept(this);
        return leftInterpreted.accept(this, rightInterpreted);
    }

    @Override
    public Value visit(ExpressionArithmeticMultiply e) {
        return null;
    }

    @Override
    public Value visit(ExpressionArithmeticSubtract e) {
        return null;
    }

    @Override
    public Value visit(ExpressionArithmeticSum e) {
        return null;
    }

    @Override
    public BoolValue visit(ExpressionComparisonEq e) {
        Value leftInterpreted = e.left.accept(this);
        Value rightInterpreted = e.right.accept(this);
        return new BoolValue(leftInterpreted.equals(rightInterpreted));
    }

    @Override
    public Value visit(ExpressionComparisonGE e) {
        return null;
    }

    @Override
    public Value visit(ExpressionComparisonGT e) {
        return null;
    }

    @Override
    public Value visit(ExpressionComparisonLE e) {
        return null;
    }

    @Override
    public Value visit(ExpressionComparisonLT e) {
        return null;
    }

    @Override
    public Value visit(ExpressionLogicalAnd e) {
        return null;
    }

    @Override
    public Value visit(ExpressionLogicalOr e) {
        return null;
    }

    @Override
    public Value visit(ExpressionUnaryNot e) {
        return null;
    }

    @Override
    public Value visit(ExpressionUnaryNeg e) {
        return null;
    }

    @Override
    public Value visit(ExpressionVariableBoolean e) {
        return null;
    }

    @Override
    public Value visit(ExpressionVariableDate e) {
        return null;
    }
}