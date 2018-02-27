package ql.ast.type;

import ql.visitors.checker.operationtypes.NumericAddSubtract;
import ql.visitors.checker.operationtypes.NumericDivide;
import ql.visitors.checker.operationtypes.NumericMultiply;
import ql.visitors.checker.operationtypes.TypeComparison;

public abstract class Numeric extends Type {

    @Override
    public String toString() {
        return name();
    }

    public static String name() {
        return "numeric";
    }

    @Override
    public boolean equals(Type t) {
        return false;
    }

    @Override
    public boolean isNumeric() {
        return true;
    }

    @Override
    public Type add(Type secondOperand) {
        return secondOperand.accept(new NumericAddSubtract(this));
    }

    @Override
    public Type subtract(Type secondOperand) {
        return secondOperand.accept(new NumericAddSubtract(this));
    }

    @Override
    public Type multiply(Type secondOperand) {
        return secondOperand.accept(new NumericMultiply(this));
    }

    @Override
    public Type divide(Type secondOperand) {
        return secondOperand.accept(new NumericDivide());
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
