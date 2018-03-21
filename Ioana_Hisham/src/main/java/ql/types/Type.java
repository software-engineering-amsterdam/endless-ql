package ql.types;

import ql.ast.Node;
import ql.visitors.TypeVisitor;

import java.lang.*;
import java.lang.String;

public abstract class Type extends Node {
    private final String name;
    public Type(int lineNumber, String name) {
        super(lineNumber);
        this.name = name;
    }

    public boolean isBoolean() {
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract <T> T accept(TypeVisitor<T> visitor);
}
