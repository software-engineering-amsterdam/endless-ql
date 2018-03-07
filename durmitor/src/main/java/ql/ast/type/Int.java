package ql.ast.type;

import ql.ast.expression.literal.Literal;
import ql.evaluator.value.parse.ToInt;
import ql.visitors.interfaces.TypeVisitor;

public class Int extends Numeric {

    @Override
    public String toString() {
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
    public void accept(TypeVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public Literal<?> parse(Literal<?> value) {
        return value.accept(new ToInt());
    }
}
