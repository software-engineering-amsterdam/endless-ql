package ql.ast.type;

import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.Literal;
import ql.evaluator.value.parse.ToDate;
import ql.visitors.interfaces.TypeVisitor;

public class Date extends Type {

    @Override
    public String toString() {
        return "date";
    }

    @Override
    public boolean equals(Type t) {
        return t.isDate();
    }

    @Override
    public boolean isDate() {
        return true;
    }
    
    @Override
    public <T> T accept(TypeVisitor<T> visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public DateLiteral parse(Literal<?> value) {
        return (DateLiteral) value.accept(new ToDate());
    }
}
