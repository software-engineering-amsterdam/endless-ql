package ql.ast.expression;

import ql.ast.type.Type;
import ql.value.Date;
import ql.visitors.interfaces.ExpressionVisitor;

public class DateLiteral extends Literal {

    private Date value;
    
    public DateLiteral() { 
        this.value = new Date();
    }
    
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
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public Date getValue() {
        return value;
    }
}
