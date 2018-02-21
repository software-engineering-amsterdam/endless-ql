package ql.ast.expression;

import java.util.Date;

import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;

public class DateLiteral extends Literal<Date> {

    private java.util.Date value;
    
    public DateLiteral() { 
        this.value = new Date();
    }
    
    @SuppressWarnings("deprecation")
    public DateLiteral(String value) {
        this.value = new Date(value);
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
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public java.util.Date getValue() {
        return value;
    }
}
