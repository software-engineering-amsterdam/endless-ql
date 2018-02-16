package ql.ast.expression;

import java.util.Date;

public class DateLiteral extends Literal<Date> {

    private Date value;
    
    public DateLiteral() { 
        this.value = new Date();
    }
    
    public DateLiteral(String value) {
        this.value = new Date(value);
    }

    @Override
    public String toString() {
        return String.format("%td-%tm-%tY", value,value,value);
    }

    @Override
    public Date getValue() {
        return value;
    }

}
