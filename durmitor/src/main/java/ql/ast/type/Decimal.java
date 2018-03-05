package ql.ast.type;

import ql.ast.expression.literal.Literal;
import ql.evaluator.value.parse.ToDecimal;
import ql.visitors.interfaces.TypeVisitor;

public class Decimal extends Numeric {

    @Override
    public String toString() {
        return "decimal";
    }

    @Override
    public boolean equals(Type t) {
        return t.isDecimal();
    }

    @Override
    public boolean isDecimal() {
        return true;
    }
    
    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public Literal<?> parse(Literal<?> value) {
        return value.accept(new ToDecimal());
    }
}
