package ql.visitors.checker.operationtypes;

import ql.ast.type.Bool;
import ql.ast.type.Date;
import ql.ast.type.Decimal;
import ql.ast.type.Int;
import ql.ast.type.Money;
import ql.ast.type.Str;
import ql.ast.type.Type;
import ql.ast.type.Undefined;
import ql.visitors.interfaces.TypeVisitor;

public class TypeComparison implements TypeVisitor<Type> {

    private Type type;
    
    public TypeComparison(Type type) {
        this.type = type;
    }
    
    @Override
    public Type visit(Bool type) {
        return this.type.isBoolean()? new Bool() : new Undefined();
    }

    @Override
    public Type visit(Str type) {
        return this.type.isString()? new Bool() : new Undefined();
    }

    @Override
    public Type visit(Int type) {
        return this.type.isNumeric()? new Bool() : new Undefined();
    }

    @Override
    public Type visit(Decimal type) {
        return this.type.isNumeric()? new Bool() : new Undefined();
    }

    @Override
    public Type visit(Money type) {
        return this.type.isMoney()? new Bool() : new Undefined();
    }

    @Override
    public Type visit(Date type) {
        return this.type.isDate()? new Bool() : new Undefined();
    }

    @Override
    public Type visit(Undefined type) {
        return type;
    }
}
