package ql.visitors;

import ql.types.String;

public interface TypeVisitor<T> {
    T visit(String string);
}
