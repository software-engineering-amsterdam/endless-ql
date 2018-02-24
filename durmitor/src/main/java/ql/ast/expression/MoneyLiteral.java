package ql.ast.expression;

import ql.ast.type.Money;
import ql.ast.type.Type;
import ql.value.Value;
import ql.visitors.interfaces.ExpressionVisitor;

public class MoneyLiteral extends Literal {

    private Value<String> value;
    
    public MoneyLiteral() { 
        this.value = new ql.value.Money();
    }
    
    public MoneyLiteral(String value) { 
        this.value = new ql.value.Money(value);
    }

    @Override
    public Type getType() {
        return new Money();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Value<String> getValue() {
        return value;
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }
}
