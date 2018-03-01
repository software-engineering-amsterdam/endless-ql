package ql.ast.expression.literal;

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
import ql.visitors.interfaces.ExpressionVisitor;
import ql.visitors.interfaces.ValueVisitor;

public class DecimalLiteral extends NumberLiteral {

    private double value;

    public DecimalLiteral() {
        this.value = 0.00;
    }

    public DecimalLiteral(String value) {
        this.value = Double.parseDouble(value);
    }

    public DecimalLiteral(double value) {
        this.value = value;
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
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Literal<?> accept(ValueVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Literal<?> negative() {
        return new DecimalLiteral(value * -1);
    }

    @Override
    public Literal<?> positive() {
        return this;
    }

    @Override
    public Literal<?> add(Literal<?> secondOperand) {
        return secondOperand.accept(new DecimalAdd(this));
    }

    @Override
    public Literal<?> subtract(Literal<?> secondOperand) {
        return secondOperand.accept(new DecimalSubtract(this));
    }

    @Override
    public Literal<?> multiply(Literal<?> secondOperand) {
        return secondOperand.accept(new DecimalMultiply(this));
    }

    @Override
    public Literal<?> divide(Literal<?> secondOperand) {
        return secondOperand.accept(new DecimalDivide(this));
    }

    @Override
    public Literal<?> less(Literal<?> secondOperand) {
        return secondOperand.accept(new DecimalLess(this));
    }

    @Override
    public Literal<?> lessEqual(Literal<?> secondOperand) {
        return secondOperand.accept(new DecimalLessEqual(this));
    }

    @Override
    public Literal<?> greater(Literal<?> secondOperand) {
        return secondOperand.accept(new DecimalGreater(this));
    }

    @Override
    public Literal<?> greaterEqual(Literal<?> secondOperand) {
        return secondOperand.accept(new DecimalGreaterEqual(this));
    }

    @Override
    public Literal<?> equal(Literal<?> secondOperand) {
        return secondOperand.accept(new DecimalEqual(this));
    }

    @Override
    public Literal<?> notEqual(Literal<?> secondOperand) {
        return secondOperand.accept(new DecimalNotEqual(this));
    }
}
