package ql.ast.expression;

import java.util.Date;

import ql.ast.type.Type;
import ql.value.Value;
import ql.visitors.interfaces.ExpressionVisitor;

public class DateLiteral extends Literal {

    private Value<Date> value;
    
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
    public Value<Date> getValue() {
        return value;
    }
}
