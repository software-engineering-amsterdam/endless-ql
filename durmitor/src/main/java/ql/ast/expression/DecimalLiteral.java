package ql.ast.expression;

import ql.ast.type.Type;
import ql.evaluator.value.Decimal;
import ql.evaluator.value.Value;
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
