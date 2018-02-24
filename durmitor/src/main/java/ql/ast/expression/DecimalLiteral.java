package ql.ast.expression;

import ql.ast.type.Decimal;
import ql.ast.type.Type;
import ql.value.Value;
import ql.visitors.interfaces.ExpressionVisitor;

public class DecimalLiteral extends Literal {

    private Value<Double> value;
    
    public DecimalLiteral() { 
        this.value = new ql.value.Decimal();
    }
    
    public DecimalLiteral(String value) { 
        this.value = new ql.value.Decimal(value);
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
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Value<Double> getValue() {
        return value;
    }
}
