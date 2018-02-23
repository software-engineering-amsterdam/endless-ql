package ql.ast.type;

import ql.value.Value;

public class Decimal extends Numeric {

    @Override
    public String toString() {
        return "decimal";
    }

    @Override
    public boolean equals(Type t) {
        return t.isDecimal();
    }

    @Override
    public boolean isDecimal() {
        return true;
    }

    @Override
    public Value<?> toValue() {
        return new ql.value.Decimal();
    }
}
