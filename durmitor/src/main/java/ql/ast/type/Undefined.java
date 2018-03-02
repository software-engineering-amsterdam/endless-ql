package ql.ast.type;

import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.visitors.interfaces.TypeVisitor;

public class Undefined extends Type {

    @Override
    public String toString() {
        return name();
    }

    public static String name() {
        return "undefined";
    }

    @Override
    public boolean equals(Type t) {
        return t.isUndefined();
    }

    @Override
    public boolean isUndefined() {
        return true;
    }
    
    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
    
    public Literal<?> parse(Literal<?> value) {
        return new UndefinedLiteral();
    }

    @Override
    public Literal<?> parse(String value) {
        // TODO Auto-generated method stub
        return null;
    }
}
