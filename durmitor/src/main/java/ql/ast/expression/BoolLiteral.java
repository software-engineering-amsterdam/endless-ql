package ql.ast.expression;

import ql.ast.type.Bool;
import ql.ast.type.Type;
import ql.value.Value;
import ql.visitors.interfaces.ExpressionVisitor;

public class BoolLiteral extends Literal {

    private Value<Boolean> value;
    
    public BoolLiteral() { 
        this.value = new ql.value.Bool();
    }
    
    public BoolLiteral(String value) { 
        this.value = new ql.value.Bool(value);
    }

    @Override
    public Type getType() {
        return new Bool();
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
    public Value<Boolean> getValue() {
        return value;
    }
}
