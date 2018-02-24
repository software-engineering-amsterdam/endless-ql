package ql.ast.expression;

import ql.ast.type.Int;
import ql.ast.type.Type;
import ql.value.Value;
import ql.visitors.interfaces.ExpressionVisitor;

public class IntLiteral extends Literal {

    private Value<Integer> value;
    
    public IntLiteral() { 
        this.value = new ql.value.Int();
    }
    
    public IntLiteral(String value) { 
        this.value = new ql.value.Int(value);
    }

    @Override
    public Type getType() {
        return new Int();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Value<Integer> getValue() {
        return value;
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }
}
