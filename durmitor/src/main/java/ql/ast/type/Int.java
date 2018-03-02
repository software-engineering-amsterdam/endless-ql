package ql.ast.type;

import ql.ast.expression.literal.Literal;
import ql.evaluator.value.parse.ToInt;
import ql.visitors.interfaces.TypeVisitor;

public class Int extends Numeric {

    @Override
    public String toString() {
        return name();
    }

    public static String name() {
        return "integer";
    }

    @Override
    public boolean equals(Type t) {
        return t.isInteger();
    }

    @Override
    public boolean isInteger() {
        return true;
    }
    
    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public Literal<?> parse(Literal<?> value) {
        return value.accept(new ToInt());
    }

    @Override
    public Literal<?> parse(String value) {
        // TODO Auto-generated method stub
        return null;
    }
}
