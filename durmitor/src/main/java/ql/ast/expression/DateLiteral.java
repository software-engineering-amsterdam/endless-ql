package ql.ast.expression;

import java.time.LocalDate;

import ql.ast.type.Type;
import ql.value.Value;
import ql.visitors.interfaces.ExpressionVisitor;

public class DateLiteral extends Literal {

    private Value<LocalDate> value;
    
    public DateLiteral() { 
        this.value = new ql.value.Date();
    }
    
    public DateLiteral(String value) {
        this.value = new ql.value.Date(value);
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Date();
    }

    @Override
    public String toString() {
        return String.format("%td-%tm-%tY", value,value,value);
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public Value<LocalDate> getValue() {
        return value;
    }
}
