package ql.ast.type;

import ql.ast.expression.literal.Literal;
import ql.evaluator.value.parse.ToBool;
import ql.visitors.interfaces.TypeVisitor;

public class Bool extends Type {

    @Override
    public String toString() {
        return "boolean";
    }

    @Override
    public boolean equals(Type t) {
        return t.isBoolean();
    }

    @Override
    public boolean isBoolean() {
        return true;
    }
    
    @Override
    public void accept(TypeVisitor visitor) {
        visitor.visit(this);
    }
    
    public Literal<?> parse(Literal<?> value) {
        return value.accept(new ToBool());
    }
}
