package ql.value;

import java.time.LocalDate;

import ql.ast.type.Type;

public class Date extends Value<LocalDate> {

    private LocalDate value;
    
    public Date() { 
        this.value = null;
    }
    
    public Date(String value) {
        this.value = LocalDate.parse(value);
    }

    public Date(LocalDate localDate) {
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public LocalDate getValue() {
        return value;
    }
    
    @Override
    public void setValue(LocalDate value) {
        this.value = value;
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Date();
    }
}
