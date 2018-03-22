package ql.types;

import ql.ast.Node;
import ql.visitors.TypeVisitor;

public abstract class Type extends Node {

    public Type(int lineNumber) {
        super(lineNumber);
    }

    public boolean isBoolean() {
        return false;
    }

    public abstract <T> T accept(TypeVisitor<T> visitor);
}
