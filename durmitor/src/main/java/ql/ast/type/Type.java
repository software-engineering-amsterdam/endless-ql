package ql.ast.type;

import ql.ast.QLNode;
import ql.ast.expression.literal.Literal;
import ql.visitors.interfaces.TypeVisitable;

public abstract class Type extends QLNode implements TypeVisitable {

    public abstract String toString();
    
    public abstract Literal<?> parse(Literal<?> value);
    
    public abstract boolean equals(Type t);

    public boolean isBoolean() {
        return false;
    }

    public boolean isInteger() {
        return false;
    }

    public boolean isString() {
        return false;
    }

    public boolean isDecimal() {
        return false;
    }

    public boolean isDate() {
        return false;
    }

    public boolean isMoney() {
        return false;
    }

    public boolean isNumeric() {
        return false;
    }

    public boolean isUndefined() {
        return false;
    }
}
