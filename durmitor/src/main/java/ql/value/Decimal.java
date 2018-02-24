package ql.value;

import ql.ast.type.Type;

public class Decimal extends Value<Double> {

    private double value;
    
    public Decimal() { 
        this.value = 0.00;
    }
    
    public Decimal(String value) { 
        this.value = Double.parseDouble(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }
    
    @Override
    public Type getType() {
        return new ql.ast.type.Decimal();
    }
}
