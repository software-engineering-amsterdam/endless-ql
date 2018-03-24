package ql.visitors;

import ql.types.Boolean;
import ql.types.String;

public interface TypeVisitor<T> {
    T visit(Boolean bool);
    T visit(String string);
}
