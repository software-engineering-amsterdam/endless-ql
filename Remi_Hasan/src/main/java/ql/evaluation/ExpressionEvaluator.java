package ql.evaluation;

import ql.analysis.SymbolTable;
import ql.evaluation.value.*;
import ql.model.expression.Expression;
import ql.model.expression.ExpressionIdentifier;
import ql.model.expression.binary.*;
import ql.model.expression.unary.ExpressionUnaryNeg;
import ql.model.expression.unary.ExpressionUnaryNot;
import ql.model.expression.variable.*;

public class ExpressionEvaluator implements IExpressionVisitor<Value> {

    private SymbolTable symbolTable;

    public ExpressionEvaluator(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public Value visit(Expression expression) {
        return expression.accept(this);
    }

    @Override
    public Value visit(ExpressionArithmeticDivide expression) {
        Value leftInterpreted = expression.left.accept(this);
        Value rightInterpreted = expression.right.accept(this);
        return leftInterpreted.divide(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionArithmeticMultiply expression) {
        Value leftInterpreted = expression.left.accept(this);
        Value rightInterpreted = expression.right.accept(this);
        return leftInterpreted.multiply(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionArithmeticSubtract expression) {
        Value leftInterpreted = expression.left.accept(this);
        Value rightInterpreted = expression.right.accept(this);
        return leftInterpreted.subtract(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionArithmeticSum expression) {
        Value leftInterpreted = expression.left.accept(this);
        Value rightInterpreted = expression.right.accept(this);
        return leftInterpreted.sum(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionComparisonEq expression) {
        Value leftInterpreted = expression.left.accept(this);
        Value rightInterpreted = expression.right.accept(this);
        return leftInterpreted.eq(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionComparisonGE expression) {
        Value leftInterpreted = expression.left.accept(this);
        Value rightInterpreted = expression.right.accept(this);
        return leftInterpreted.ge(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionComparisonGT expression) {
        Value leftInterpreted = expression.left.accept(this);
        Value rightInterpreted = expression.right.accept(this);
        return leftInterpreted.gt(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionComparisonLE expression) {
        Value leftInterpreted = expression.left.accept(this);
        Value rightInterpreted = expression.right.accept(this);
        return leftInterpreted.le(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionComparisonLT expression) {
        Value leftInterpreted = expression.left.accept(this);
        Value rightInterpreted = expression.right.accept(this);
        return leftInterpreted.lt(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionLogicalAnd expression) {
        Value leftInterpreted = expression.left.accept(this);
        Value rightInterpreted = expression.right.accept(this);
        return leftInterpreted.and(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionLogicalOr expression) {
        Value leftInterpreted = expression.left.accept(this);
        Value rightInterpreted = expression.right.accept(this);
        return leftInterpreted.or(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionUnaryNot expression) {
        Value valueInterpreted = expression.value.accept(this);
        return valueInterpreted.not();
    }

    @Override
    public Value visit(ExpressionUnaryNeg expression) {
        Value valueInterpreted = expression.value.accept(this);
        return valueInterpreted.neg();
    }

    @Override
    public Value visit(ExpressionVariableBoolean expression) {
        return new BoolValue(expression.value);
    }

    @Override
    public Value visit(ExpressionVariableDate expression) {
        return new DateValue(expression.value);
    }

    @Override
    public Value visit(ExpressionVariableInteger expression) {
        return new NumValue(expression.value);
    }

    @Override
    public Value visit(ExpressionVariableDecimal expression) {
        return new NumValue(expression.value);
    }

    @Override
    public Value visit(ExpressionVariableMoney expression) {
        return new NumValue(expression.value);
    }

    @Override
    public Value visit(ExpressionVariableString expression) {
        return new StringValue(expression.value);
    }

    @Override
    public Value visit(ExpressionVariableUndefined expression) {
        return new UndefinedValue();
    }

    @Override
    public Value visit(ExpressionIdentifier expression) {
        return this.symbolTable.getExpression(expression.identifier).accept(this);
    }
}