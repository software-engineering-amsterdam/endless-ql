package ql.evaluator.value;

import ql.ast.type.Type;
import ql.evaluator.arithmetic.add.DecimalAdd;
import ql.evaluator.arithmetic.divide.DecimalDivide;
import ql.evaluator.arithmetic.multiply.DecimalMultiply;
import ql.evaluator.arithmetic.subtract.DecimalSubtract;
import ql.evaluator.comparisons.equal.DecimalEqual;
import ql.evaluator.comparisons.greater.DecimalGreater;
import ql.evaluator.comparisons.greaterequal.DecimalGreaterEqual;
import ql.evaluator.comparisons.less.DecimalLess;
import ql.evaluator.comparisons.lessequal.DecimalLessEqual;
import ql.evaluator.comparisons.notequal.DecimalNotEqual;
import ql.visitors.interfaces.ValueVisitor;

public class Decimal extends Numeric {

    private double value;

    public Decimal() {
        this.value = 0.00;
    }

    public Decimal(String value) {
        this.value = Double.parseDouble(value);
    }

    public Decimal(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Decimal();
    }

    @Override
    public Value<?> accept(ValueVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Value<?> negative() {
        return new Decimal(value * -1);
    }

    @Override
    public Value<?> positive() {
        return this;
    }

    @Override
    public Value<?> add(Value<?> secondOperand) {
        return secondOperand.accept(new DecimalAdd(this));
    }

    @Override
    public Value<?> subtract(Value<?> secondOperand) {
        return secondOperand.accept(new DecimalSubtract(this));
    }

    @Override
    public Value<?> multiply(Value<?> secondOperand) {
        return secondOperand.accept(new DecimalMultiply(this));
    }

    @Override
    public Value<?> divide(Value<?> secondOperand) {
        return secondOperand.accept(new DecimalDivide(this));
    }

    @Override
    public Value<?> less(Value<?> secondOperand) {
        return secondOperand.accept(new DecimalLess(this));
    }

    @Override
    public Value<?> lessEqual(Value<?> secondOperand) {
        return secondOperand.accept(new DecimalLessEqual(this));
    }

    @Override
    public Value<?> greater(Value<?> secondOperand) {
        return secondOperand.accept(new DecimalGreater(this));
    }

    @Override
    public Value<?> greaterEqual(Value<?> secondOperand) {
        return secondOperand.accept(new DecimalGreaterEqual(this));
    }

    @Override
    public Value<?> equal(Value<?> secondOperand) {
        return secondOperand.accept(new DecimalEqual(this));
    }

    @Override
    public Value<?> notEqual(Value<?> secondOperand) {
        return secondOperand.accept(new DecimalNotEqual(this));
    }
}
