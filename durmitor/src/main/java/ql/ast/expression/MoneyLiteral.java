package ql.ast.expression;

import ql.ast.type.Type;
import ql.evaluator.value.Money;
import ql.evaluator.value.Value;
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
        return value.toString();
    }

    @Override
    public Value<?> evaluate() {
        return value;
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }
}
