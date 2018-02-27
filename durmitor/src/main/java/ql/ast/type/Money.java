package ql.ast.type;

import ql.evaluator.value.Value;
import ql.evaluator.value.parse.ToMoney;
import ql.visitors.checker.operationtypes.MoneyAddSubtract;
import ql.visitors.checker.operationtypes.MoneyMultiplyDivide;
import ql.visitors.checker.operationtypes.TypeComparison;
import ql.visitors.interfaces.TypeVisitor;

public class Money extends Type {

    @Override
    public String toString() {
        return name();
    }

    public static String name() {
        return "money";
    }

    @Override
    public boolean equals(Type t) {
        return t.isMoney();
    }

    @Override
    public boolean isMoney() {
        return true;
    }

    @Override
    public Value<?> toValue() {
        return new ql.evaluator.value.Money();
    }
    
    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public Value<?> parse(Value<?> value) {
        return value.accept(new ToMoney());
    }

    @Override
    public Type negative() {
        return this;
    }

    @Override
    public Type positive() {
        return this;
    }

    @Override
    public Type add(Type secondOperand) {
        return secondOperand.accept(new MoneyAddSubtract());
    }

    @Override
    public Type subtract(Type secondOperand) {
        return secondOperand.accept(new MoneyAddSubtract());
    }

    @Override
    public Type multiply(Type secondOperand) {
        return secondOperand.accept(new MoneyMultiplyDivide());
    }

    @Override
    public Type divide(Type secondOperand) {
        return secondOperand.accept(new MoneyMultiplyDivide());
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
