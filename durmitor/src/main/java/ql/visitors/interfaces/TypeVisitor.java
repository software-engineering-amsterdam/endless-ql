package ql.visitors.interfaces;

import ql.ast.type.Bool;
import ql.ast.type.Date;
import ql.ast.type.Decimal;
import ql.ast.type.Int;
import ql.ast.type.Money;
import ql.ast.type.Str;
import ql.ast.type.Undefined;

public interface TypeVisitor<T> {

    public T visit(Bool type);
    public T visit(Str type);
    public T visit(Int type);
    public T visit(Decimal type);
    public T visit(Money type);
    public T visit(Date type);
    public T visit(Undefined type);
}
