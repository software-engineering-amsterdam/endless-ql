package ql.ast.type;

import ql.ast.expression.literal.IntLiteral;
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
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public IntLiteral parse(Literal<?> value) {
        return (IntLiteral) value.accept(new ToInt());
    }
    
    public static IntLiteral parseInt(Literal<?> value) {
        return new Int().parse(value);
    }
}
