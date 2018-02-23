package ql.ast.type;

import java.time.LocalDate;

public class Date extends Type {

    private String name = "";
    private LocalDate localDate = LocalDate.now();
    
    @Override
    public void setValue(String value) {
        this.localDate = LocalDate.parse(value);
    }
    
    public void setDate(LocalDate localDate) {
        this.localDate = localDate;
    }
    
    public LocalDate getDate() {
        return localDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override public String toString() { return "date"; }
    @Override public boolean equals(Type t) { return t.isDate(); }
    @Override public boolean isDate() { return true; }
    
    // Date as first operand
    @Override public Type less(Type secondOperand) { return secondOperand.greaterThan(this); }
    @Override public Type lessEqual(Type secondOperand) { return secondOperand.greaterThanEqualTo(this); }
    @Override public Type greater(Type secondOperand) { return secondOperand.lessThan(this); }
    @Override public Type greaterEqual(Type secondOperand) { return secondOperand.lessThanEqualTo(this); }
    
    // Date as second operand
    @Override public Type greaterThan(Date firstOperand) { return new Bool(); }
    @Override public Type greaterThanEqualTo(Date firstOperand) { return new Bool(); }
    @Override public Type lessThan(Date firstOperand) { return new Bool(); }
    @Override public Type lessThanEqualTo(Date firstOperand) { return new Bool(); }
}
