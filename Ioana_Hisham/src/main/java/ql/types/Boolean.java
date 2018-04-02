package ql.types;

import ql.visitors.TypeVisitor;

import java.lang.*;

public class Boolean extends Type {
    public Boolean(int lineNumber, java.lang.String name) {
        super(lineNumber, name);
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean isBoolean() {
        return true;
    }
}
