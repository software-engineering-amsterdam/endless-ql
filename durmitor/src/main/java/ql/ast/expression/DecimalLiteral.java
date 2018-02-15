package ql.ast.expression;

public class DecimalLiteral extends Literal<Double> {

    private double value;
    
    public DecimalLiteral() { 
        this.value = 0.00;
    }
    
    public DecimalLiteral(String value) { 
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

}
