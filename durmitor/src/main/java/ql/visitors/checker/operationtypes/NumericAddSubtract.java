package ql.visitors.checker.operationtypes;

import ql.ast.type.Bool;
import ql.ast.type.Date;
import ql.ast.type.Decimal;
import ql.ast.type.Int;
import ql.ast.type.Money;
import ql.ast.type.Numeric;
import ql.ast.type.Str;
import ql.ast.type.Type;
import ql.ast.type.Undefined;
import ql.visitors.interfaces.TypeVisitor;

public class NumericAddSubtract implements TypeVisitor<Type> {

    private Numeric type;
    
    public NumericAddSubtract(Numeric type) {
        this.type = type;
    }
    
    @Override
    public Type visit(Bool type) {
        return new Undefined();
    }

    @Override
    public Type visit(Str type) {
        return new Undefined();
    }

    @Override
    public Type visit(Int type) {
        return this.type;
    }

    @Override
    public Type visit(Decimal type) {
        return type;
    }

    @Override
    public Type visit(Money type) {
        return new Undefined();
    }

    @Override
    public Type visit(Date type) {
        return new Undefined();
    }

    @Override
    public Type visit(Undefined type) {
        return type;
    }
}
