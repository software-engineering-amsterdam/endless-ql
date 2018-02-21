package ql.ast.expression;

import ql.ast.type.Decimal;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;

public class DecimalLiteral extends Literal<Double> {

    private double value;
    
    public DecimalLiteral() { 
        this.value = 0.00;
    }
    
    public DecimalLiteral(String value) { 
        this.value = Double.parseDouble(value);
    }

    @Override
    public Type getType() {
        return new Decimal();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Double getValue() {
        return value;
    }
}
