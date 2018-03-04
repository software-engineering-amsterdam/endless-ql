package ql.ast.expression.literal;

import ql.ast.type.Int;
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
import ql.visitors.interfaces.ExpressionVisitable;
import ql.visitors.interfaces.ExpressionVisitor;
import ql.visitors.interfaces.ValueVisitor;

public class IntLiteral extends NumberLiteral implements ExpressionVisitable {

    private int value;

    public IntLiteral() {
        this.value = 0;
    }

    public IntLiteral(String value) {
        this.value = Integer.parseInt(value);
    }

    public IntLiteral(int value) {
        this.value = value;
    }
    
    @Override
    public Integer getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Type getType() {
        return new Int();
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
        return new IntLiteral(value * -1);
    }

    @Override
    public Literal<?> positive() {
        return this;
    }

    @Override
    public Literal<?> add(Literal<?> secondOperand) {
        return secondOperand.accept(new IntAdd(this));
    }

    @Override
    public Literal<?> subtract(Literal<?> secondOperand) {
        return secondOperand.accept(new IntSubtract(this));
    }

    @Override
    public Literal<?> multiply(Literal<?> secondOperand) {
        return secondOperand.accept(new IntMultiply(this));
    }

    @Override
    public Literal<?> divide(Literal<?> secondOperand) {
        return secondOperand.accept(new IntDivide(this));
    }

    @Override
    public Literal<?> less(Literal<?> secondOperand) {
        return secondOperand.accept(new IntLess(this));
    }

    @Override
    public Literal<?> lessEqual(Literal<?> secondOperand) {
        return secondOperand.accept(new IntLessEqual(this));
    }

    @Override
    public Literal<?> greater(Literal<?> secondOperand) {
        return secondOperand.accept(new IntGreater(this));
    }

    @Override
    public Literal<?> greaterEqual(Literal<?> secondOperand) {
        return secondOperand.accept(new IntGreaterEqual(this));
    }

    @Override
    public Literal<?> equal(Literal<?> secondOperand) {
        return secondOperand.accept(new IntEqual(this));
    }

    @Override
    public Literal<?> notEqual(Literal<?> secondOperand) {
        return secondOperand.accept(new IntNotEqual(this));
    }
}
