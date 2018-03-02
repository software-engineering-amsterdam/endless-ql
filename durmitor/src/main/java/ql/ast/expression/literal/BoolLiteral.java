package ql.ast.expression.literal;

import ql.ast.type.Type;
import ql.evaluator.booleans.BoolAnd;
import ql.evaluator.booleans.BoolOr;
import ql.evaluator.comparisons.equal.BoolEqual;
import ql.evaluator.comparisons.notequal.BoolNotEqual;
import ql.visitors.interfaces.ExpressionVisitor;
import ql.visitors.interfaces.ValueVisitor;

public class BoolLiteral extends Literal<Boolean> {

    private boolean value;

    public BoolLiteral() {
        this.value = false;
    }

    public BoolLiteral(String value) {
        this.value = Boolean.parseBoolean(value);
    }

    public BoolLiteral(boolean value) {
        this.value = value;
    }
    
    @Override
    public Boolean getValue() {
        return value;
    }
    
    public void setValue(Boolean value) {
        this.value = value;
        setChanged();
        notifyObservers();
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Bool();
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
    public Literal<?> negation() {
        return new BoolLiteral(!value);
    }
    
    @Override
    public Literal<?> and(Literal<?> secondOperand) {
        return secondOperand.accept(new BoolAnd(this));
    }
    
    @Override
    public Literal<?> or(Literal<?> secondOperand) {
        return secondOperand.accept(new BoolOr(this));
    }

    @Override
    public Literal<?> equal(Literal<?> secondOperand) {
        return secondOperand.accept(new BoolEqual(this));
    }

    @Override
    public Literal<?> notEqual(Literal<?> secondOperand) {
        return secondOperand.accept(new BoolNotEqual(this));
    }
}
