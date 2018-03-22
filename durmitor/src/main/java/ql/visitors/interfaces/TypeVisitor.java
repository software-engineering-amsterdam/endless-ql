package ql.visitors.interfaces;

import ql.ast.type.Bool;
import ql.ast.type.Date;
import ql.ast.type.Decimal;
import ql.ast.type.Int;
import ql.ast.type.Money;
import ql.ast.type.Str;
import ql.ast.type.Undefined;

public interface TypeVisitor {

    public void visit(Bool type);
    public void visit(Str type);
    public void visit(Int type);
    public void visit(Decimal type);
    public void visit(Money type);
    public void visit(Date type);
    public void visit(Undefined type);
}
