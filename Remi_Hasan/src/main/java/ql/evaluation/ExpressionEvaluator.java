package ql.evaluation;

import ql.analysis.Lookup;
import ql.evaluation.value.*;
import ql.model.expression.Expression;
import ql.model.expression.ExpressionIdentifier;
import ql.model.expression.binary.*;
import ql.model.expression.unary.ExpressionUnaryNeg;
import ql.model.expression.unary.ExpressionUnaryNot;
import ql.model.expression.variable.*;

import java.util.List;

public class ExpressionEvaluator implements IExpressionVisitor<Value> {

    @Override
    public Value visit(Expression expression, List<Binding> bindings) {
        return expression.accept(this, bindings);
    }

    @Override
    public Value visit(ExpressionArithmeticDivide expression, List<Binding> bindings) {
        Value leftInterpreted = expression.left.accept(this, bindings);
        Value rightInterpreted = expression.right.accept(this, bindings);
        return leftInterpreted.divide(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionArithmeticMultiply expression, List<Binding> bindings) {
        Value leftInterpreted = expression.left.accept(this, bindings);
        Value rightInterpreted = expression.right.accept(this, bindings);
        return leftInterpreted.multiply(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionArithmeticSubtract expression, List<Binding> bindings) {
        Value leftInterpreted = expression.left.accept(this, bindings);
        Value rightInterpreted = expression.right.accept(this, bindings);
        return leftInterpreted.subtract(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionArithmeticSum expression, List<Binding> bindings) {
        Value leftInterpreted = expression.left.accept(this, bindings);
        Value rightInterpreted = expression.right.accept(this, bindings);
        return leftInterpreted.sum(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionComparisonEq expression, List<Binding> bindings) {
        Value leftInterpreted = expression.left.accept(this, bindings);
        Value rightInterpreted = expression.right.accept(this, bindings);
        return leftInterpreted.eq(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionComparisonGE expression, List<Binding> bindings) {
        Value leftInterpreted = expression.left.accept(this, bindings);
        Value rightInterpreted = expression.right.accept(this, bindings);
        return leftInterpreted.ge(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionComparisonGT expression, List<Binding> bindings) {
        Value leftInterpreted = expression.left.accept(this, bindings);
        Value rightInterpreted = expression.right.accept(this, bindings);
        return leftInterpreted.gt(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionComparisonLE expression, List<Binding> bindings) {
        Value leftInterpreted = expression.left.accept(this, bindings);
        Value rightInterpreted = expression.right.accept(this, bindings);
        return leftInterpreted.le(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionComparisonLT expression, List<Binding> bindings) {
        Value leftInterpreted = expression.left.accept(this, bindings);
        Value rightInterpreted = expression.right.accept(this, bindings);
        return leftInterpreted.lt(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionLogicalAnd expression, List<Binding> bindings) {
        Value leftInterpreted = expression.left.accept(this, bindings);
        Value rightInterpreted = expression.right.accept(this, bindings);
        return leftInterpreted.and(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionLogicalOr expression, List<Binding> bindings) {
        Value leftInterpreted = expression.left.accept(this, bindings);
        Value rightInterpreted = expression.right.accept(this, bindings);
        return leftInterpreted.or(rightInterpreted);
    }

    @Override
    public Value visit(ExpressionUnaryNot expression, List<Binding> bindings) {
        Value valueInterpreted = expression.value.accept(this, bindings);
        return valueInterpreted.not();
    }

    @Override
    public Value visit(ExpressionUnaryNeg expression, List<Binding> bindings) {
        Value valueInterpreted = expression.value.accept(this, bindings);
        return valueInterpreted.neg();
    }

    @Override
    public Value visit(ExpressionVariableBoolean expression, List<Binding> bindings) {
        return new BooleanValue(expression.value);
    }

    @Override
    public Value visit(ExpressionVariableDate expression, List<Binding> bindings) {
        return new DateValue(expression.value);
    }

    @Override
    public Value visit(ExpressionVariableInteger expression, List<Binding> bindings) {
        return new NumberValue(expression.value);
    }

    @Override
    public Value visit(ExpressionVariableDecimal expression, List<Binding> bindings) {
        return new NumberValue(expression.value);
    }

    @Override
    public Value visit(ExpressionVariableMoney expression, List<Binding> bindings) {
        return new NumberValue(expression.value);
    }

    @Override
    public Value visit(ExpressionVariableString expression, List<Binding> bindings) {
        return new StringValue(expression.value);
    }

    @Override
    public Value visit(ExpressionVariableUndefined expression, List<Binding> bindings) {
        return new UndefinedValue();
    }

    @Override
    public Value visit(ExpressionIdentifier expression, List<Binding> bindings) {
        return Lookup.lookup(expression, bindings).accept(this, bindings);
    }
}