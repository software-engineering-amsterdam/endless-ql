package ql.ast.type;

import ql.value.Value;

public class Str extends Type {

    private ql.value.Str value;

    public Value<String> getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public Value<?> toValue() {
        return new ql.value.Str();
    }

    @Override
    public boolean equals(Type t) {
        return t.isString();
    }

    @Override
    public boolean isString() {
        return true;
    }
}
