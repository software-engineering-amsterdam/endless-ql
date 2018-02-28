package ql.evaluator.value;

import ql.ast.type.Type;
import ql.evaluator.arithmetic.add.IntAdd;
import ql.evaluator.arithmetic.divide.IntDivide;
import ql.evaluator.arithmetic.multiply.IntMultiply;
import ql.evaluator.arithmetic.subtract.IntSubtract;
import ql.evaluator.comparisons.equal.IntEqual;
import ql.evaluator.comparisons.greater.IntGreater;
import ql.evaluator.comparisons.greaterequal.IntGreaterEqual;
import ql.evaluator.comparisons.less.IntLess;
import ql.evaluator.comparisons.lessequal.IntLessEqual;
import ql.evaluator.comparisons.notequal.IntNotEqual;
import ql.visitors.interfaces.ValueVisitor;

public class Int extends Numeric {

    private int value;

    public Int() {
        this.value = 0;
    }

    public Int(String value) {
        this.value = Integer.parseInt(value);
    }

    public Int(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Int();
    }

    @Override
    public Value<?> accept(ValueVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Value<?> negative() {
        return new Int(value * -1);
    }

    @Override
    public Value<?> positive() {
        return this;
    }

    @Override
    public Value<?> add(Value<?> secondOperand) {
        return secondOperand.accept(new IntAdd(this));
    }

    @Override
    public Value<?> subtract(Value<?> secondOperand) {
        return secondOperand.accept(new IntSubtract(this));
    }

    @Override
    public Value<?> multiply(Value<?> secondOperand) {
        return secondOperand.accept(new IntMultiply(this));
    }

    @Override
    public Value<?> divide(Value<?> secondOperand) {
        return secondOperand.accept(new IntDivide(this));
    }

    @Override
    public Value<?> less(Value<?> secondOperand) {
        return secondOperand.accept(new IntLess(this));
    }

    @Override
    public Value<?> lessEqual(Value<?> secondOperand) {
        return secondOperand.accept(new IntLessEqual(this));
    }

    @Override
    public Value<?> greater(Value<?> secondOperand) {
        return secondOperand.accept(new IntGreater(this));
    }

    @Override
    public Value<?> greaterEqual(Value<?> secondOperand) {
        return secondOperand.accept(new IntGreaterEqual(this));
    }

    @Override
    public Value<?> equal(Value<?> secondOperand) {
        return secondOperand.accept(new IntEqual(this));
    }

    @Override
    public Value<?> notEqual(Value<?> secondOperand) {
        return secondOperand.accept(new IntNotEqual(this));
    }
}
