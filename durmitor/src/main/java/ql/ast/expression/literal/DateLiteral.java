package ql.ast.expression.literal;

import java.time.LocalDate;

import ql.ast.type.Type;
import ql.evaluator.comparisons.equal.DateEqual;
import ql.evaluator.comparisons.greater.DateGreater;
import ql.evaluator.comparisons.greaterequal.DateGreaterEqual;
import ql.evaluator.comparisons.less.DateLess;
import ql.evaluator.comparisons.lessequal.DateLessEqual;
import ql.evaluator.comparisons.notequal.DateNotEqual;
import ql.visitors.interfaces.ExpressionVisitor;
import ql.visitors.interfaces.ValueVisitor;

public class DateLiteral extends Literal<LocalDate> {

    private LocalDate value;

    public DateLiteral() {
        this.value = LocalDate.MIN;
    }

    public DateLiteral(String value) {
        this.value = LocalDate.parse(value);
    }

    public DateLiteral(LocalDate value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public LocalDate getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Date();
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
    public Literal<?> less(Literal<?> secondOperand) {
        return secondOperand.accept(new DateLess(this));
    }

    @Override
    public Literal<?> lessEqual(Literal<?> secondOperand) {
        return secondOperand.accept(new DateLessEqual(this));
    }

    @Override
    public Literal<?> greater(Literal<?> secondOperand) {
        return secondOperand.accept(new DateGreater(this));
    }

    @Override
    public Literal<?> greaterEqual(Literal<?> secondOperand) {
        return secondOperand.accept(new DateGreaterEqual(this));
    }

    @Override
    public Literal<?> equal(Literal<?> secondOperand) {
        return secondOperand.accept(new DateEqual(this));
    }

    @Override
    public Literal<?> notEqual(Literal<?> secondOperand) {
        return secondOperand.accept(new DateNotEqual(this));
    }
}
