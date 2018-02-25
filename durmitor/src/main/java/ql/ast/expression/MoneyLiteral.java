package ql.ast.expression;

import ql.ast.type.Type;
import ql.value.Money;
import ql.visitors.interfaces.ExpressionVisitor;

public class MoneyLiteral extends Literal {

    private Money value;
    
    public MoneyLiteral() { 
        this.value = new Money();
    }
    
    public MoneyLiteral(String value) { 
        this.value = new Money(value);
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Money();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Money getValue() {
        return value;
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }
}
