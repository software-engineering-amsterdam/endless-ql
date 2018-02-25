package ql.ast.expression;

import ql.ast.type.Type;
import ql.value.Decimal;
import ql.visitors.interfaces.ExpressionVisitor;

public class DecimalLiteral extends Literal {

    private Decimal value;
    
    public DecimalLiteral() { 
        this.value = new Decimal();
    }
    
    public DecimalLiteral(String value) { 
        this.value = new Decimal(value);
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Decimal();
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
    public Decimal getValue() {
        return value;
    }
}
