package evaluation;

import analysis.SymbolTable;
import evaluation.value.*;
import model.expression.Expression;
import model.expression.ExpressionIdentifier;
import model.expression.binary.*;
import model.expression.unary.ExpressionUnaryNeg;
import model.expression.unary.ExpressionUnaryNot;
import model.expression.variable.*;

public class ExpressionEvaluator implements IASTVisitor<Value> {

    private SymbolTable symbolTable;

    public ExpressionEvaluator(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public Value visit(Expression e) {
        return e.accept(this);
    }

    @Override
    public Value visit(ExpressionArithmeticDivide e) {
        Value leftInterpreted = e.left.accept(this);
        Value rightInterpreted = e.right.accept(this);
        return leftInterpreted.divide(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionArithmeticMultiply e) {
        Value leftInterpreted = e.left.accept(this);
        Value rightInterpreted = e.right.accept(this);
        return leftInterpreted.multiply(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionArithmeticSubtract e) {
        Value leftInterpreted = e.left.accept(this);
        Value rightInterpreted = e.right.accept(this);
        return leftInterpreted.subtract(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionArithmeticSum e) {
        Value leftInterpreted = e.left.accept(this);
        Value rightInterpreted = e.right.accept(this);
        return leftInterpreted.sum(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionComparisonEq e) {
        Value leftInterpreted = e.left.accept(this);
        Value rightInterpreted = e.right.accept(this);
        return leftInterpreted.eq(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionComparisonGE e) {
        Value leftInterpreted = e.left.accept(this);
        Value rightInterpreted = e.right.accept(this);
        return leftInterpreted.ge(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionComparisonGT e) {
        Value leftInterpreted = e.left.accept(this);
        Value rightInterpreted = e.right.accept(this);
        return leftInterpreted.gt(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionComparisonLE e) {
        Value leftInterpreted = e.left.accept(this);
        Value rightInterpreted = e.right.accept(this);
        return leftInterpreted.le(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionComparisonLT e) {
        Value leftInterpreted = e.left.accept(this);
        Value rightInterpreted = e.right.accept(this);
        return leftInterpreted.lt(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionLogicalAnd e) {
        Value leftInterpreted = e.left.accept(this);
        Value rightInterpreted = e.right.accept(this);
        return leftInterpreted.and(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionLogicalOr e) {
        Value leftInterpreted = e.left.accept(this);
        Value rightInterpreted = e.right.accept(this);
        return leftInterpreted.or(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionUnaryNot e) {
        Value valueInterpreted = e.value.accept(this);
        return valueInterpreted.not();
    }

    @Override
    public Value visit(ExpressionUnaryNeg e) {
        Value valueInterpreted = e.value.accept(this);
        return valueInterpreted.neg();
    }

    @Override
    public Value visit(ExpressionVariableBoolean e) {
        return new BoolValue(e.value);
    }

    @Override
    public Value visit(ExpressionVariableDate e) {
        return new DateValue(e.value);
    }

    @Override
    public Value visit(ExpressionVariableInteger e) {
        return new NumValue(e.value);
    }

    @Override
    public Value visit(ExpressionVariableDecimal e) {
        return new NumValue(e.value);
    }

    @Override
    public Value visit(ExpressionVariableMoney e) {
        return new NumValue(e.value);
    }

    @Override
    public Value visit(ExpressionVariableString e) {
        return new StringValue(e.value);
    }

    @Override
    public Value visit(ExpressionVariableUndefined e) {
        return new UndefinedValue();
    }

    @Override
    public Value visit(ExpressionIdentifier e) {
        return this.symbolTable.getExpression(e.identifier).accept(this);
    }
}