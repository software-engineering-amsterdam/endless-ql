package ql.ast.type;

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
    public void accept(TypeVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public Literal<?> parse(Literal<?> value) {
        return value.accept(new ToDate());
    }
}
