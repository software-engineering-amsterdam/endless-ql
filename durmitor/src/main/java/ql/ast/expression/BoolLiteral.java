package ql.ast.expression;

import ql.ast.type.Bool;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;

public class BoolLiteral extends Literal<Boolean> {

    private boolean value;
    
    public BoolLiteral() { 
        this.value = false;
    }
    
    public BoolLiteral(String value) { 
        this.value = Boolean.parseBoolean(value);
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
    public Boolean getValue() {
        return value;
    }
}
