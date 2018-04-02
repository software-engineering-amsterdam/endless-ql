package ql.visitors;

import ql.types.*;
import ql.types.Boolean;
import ql.types.Integer;
import ql.types.String;

public interface TypeVisitor<T> {
    T visit(Boolean bool);
    T visit(Integer integer);
    T visit(Money money);
    T visit(String string);
    T visit(Unknown unknown);
}
