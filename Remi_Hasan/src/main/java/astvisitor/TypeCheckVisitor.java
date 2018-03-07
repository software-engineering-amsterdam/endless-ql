package astvisitor;

import expression.Expression;
import expression.ReturnType;
import expression.binary.*;
import expression.unary.ExpressionUnaryNeg;
import expression.unary.ExpressionUnaryNot;
import expression.variable.*;

public class TypeCheckVisitor implements IASTVisitor<BoolValue> {

    private boolean childrenTypeCheckSucceeeds(Expression left, Expression right) {
        BoolValue leftTypeCheckSucceed = left.accept(this);
        BoolValue rightTypeCheckSucceed = right.accept(this);

        return leftTypeCheckSucceed.value && rightTypeCheckSucceed.value;
    }

    private boolean childrenTypeCheckSucceeeds(Expression left) {
        BoolValue leftTypeCheckSucceed = left.accept(this);
        return leftTypeCheckSucceed.value;
    }

    @Override
    public BoolValue visit(ExpressionArithmeticDivide e) {
        return new BoolValue(
                childrenTypeCheckSucceeeds(e.left, e.right)
                        && e.left.getReturnType().isNumber()
                        && e.right.getReturnType().isNumber()
        );
    }

    @Override
    public BoolValue visit(ExpressionArithmeticMultiply e) {
        return new BoolValue(
                childrenTypeCheckSucceeeds(e.left, e.right)
                        && e.left.getReturnType().isNumber()
                        && e.right.getReturnType().isNumber()
        );
    }

    @Override
    public BoolValue visit(ExpressionArithmeticSubtract e) {
        return new BoolValue(
                childrenTypeCheckSucceeeds(e.left, e.right)
                        && e.left.getReturnType().isNumber()
                        && e.right.getReturnType().isNumber()
        );
    }

    @Override
    public BoolValue visit(ExpressionArithmeticSum e) {
        return new BoolValue(
                childrenTypeCheckSucceeeds(e.left, e.right)
                        && e.left.getReturnType().isNumber()
                        && e.right.getReturnType().isNumber()
        );
    }

    @Override
    public BoolValue visit(ExpressionComparisonEq e) {
        return new BoolValue(childrenTypeCheckSucceeeds(e.left, e.right));
    }

    @Override
    public BoolValue visit(ExpressionComparisonGE e) {
        return new BoolValue(
                childrenTypeCheckSucceeeds(e.left, e.right)
                        && e.left.getReturnType().isNumber()
                        && e.right.getReturnType().isNumber()
        );
    }

    @Override
    public BoolValue visit(ExpressionComparisonGT e) {
        return new BoolValue(
                childrenTypeCheckSucceeeds(e.left, e.right)
                        && e.left.getReturnType().isNumber()
                        && e.right.getReturnType().isNumber()
        );
    }

    @Override
    public BoolValue visit(ExpressionComparisonLE e) {
        return new BoolValue(
                childrenTypeCheckSucceeeds(e.left, e.right)
                        && e.left.getReturnType().isNumber()
                        && e.right.getReturnType().isNumber()
        );
    }

    @Override
    public BoolValue visit(ExpressionComparisonLT e) {
        return new BoolValue(
                childrenTypeCheckSucceeeds(e.left, e.right)
                        && e.left.getReturnType().isNumber()
                        && e.right.getReturnType().isNumber()
        );
    }

    @Override
    public BoolValue visit(ExpressionLogicalAnd e) {
        return new BoolValue(
                childrenTypeCheckSucceeeds(e.left, e.right)
                        && e.left.getReturnType() == ReturnType.BOOLEAN
                        && e.right.getReturnType() == ReturnType.BOOLEAN
        );
    }

    @Override
    public BoolValue visit(ExpressionLogicalOr e) {
        return new BoolValue(
                childrenTypeCheckSucceeeds(e.left, e.right)
                        && e.left.getReturnType() == ReturnType.BOOLEAN
                        && e.right.getReturnType() == ReturnType.BOOLEAN
        );
    }

    @Override
    public BoolValue visit(ExpressionUnaryNot e) {
        return new BoolValue(
                childrenTypeCheckSucceeeds(e.value)
                        && e.value.getReturnType() == ReturnType.BOOLEAN
        );
    }

    @Override
    public BoolValue visit(ExpressionUnaryNeg e) {
        return new BoolValue(
                childrenTypeCheckSucceeeds(e.value)
                        && e.value.getReturnType().isNumber()
        );
    }

    @Override
    public BoolValue visit(ExpressionVariableBoolean e) {
        return new BoolValue(true);
    }

    @Override
    public BoolValue visit(ExpressionVariableDate e) {
        return new BoolValue(true);
    }

    @Override
    public BoolValue visit(ExpressionVariableInteger e) {
        return new BoolValue(true);
    }

    @Override
    public BoolValue visit(ExpressionVariableDecimal e) {
        return new BoolValue(true);
    }

    @Override
    public BoolValue visit(ExpressionVariableMoney e) {
        return new BoolValue(true);
    }

    @Override
    public BoolValue visit(ExpressionVariableString e) {
        return new BoolValue(true);
    }
}
