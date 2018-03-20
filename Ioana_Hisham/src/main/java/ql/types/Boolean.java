package ql.types;

import ql.visitors.TypeVisitor;

public class Boolean extends Type {
    public Boolean(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return null;
    }
}
