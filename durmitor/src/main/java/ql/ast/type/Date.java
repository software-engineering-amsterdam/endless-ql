package ql.ast.type;

import ql.evaluator.value.Value;
import ql.evaluator.value.parse.ToDate;
import ql.visitors.checker.operationtypes.TypeComparison;
import ql.visitors.interfaces.TypeVisitor;

public class Date extends Type {

    @Override
    public String toString() {
        return name();
    }

    public static String name() {
        return "date";
    }

    @Override
    public boolean equals(Type t) {
        return t.isDate();
    }

    @Override
    public boolean isDate() {
        return true;
    }

    @Override
    public Value<?> toValue() {
        return new ql.evaluator.value.Date();
    }
    
    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public Value<?> parse(Value<?> value) {
        return value.accept(new ToDate());
    }

    @Override
    public Type less(Type secondOperand) {
        return secondOperand.accept(new TypeComparison(this));
    }

    @Override
    public Type lessEqual(Type secondOperand) {
        return secondOperand.accept(new TypeComparison(this));
    }

    @Override
    public Type greater(Type secondOperand) {
        return secondOperand.accept(new TypeComparison(this));
    }

    @Override
    public Type greaterEqual(Type secondOperand) {
        return secondOperand.accept(new TypeComparison(this));
    }

    @Override
    public Type equal(Type secondOperand) {
        return secondOperand.accept(new TypeComparison(this));
    }

    @Override
    public Type notEqual(Type secondOperand) {
        return secondOperand.accept(new TypeComparison(this));
    }
}
