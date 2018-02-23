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
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean getValue() {
        return value;
    }
    
    @Override
    public Type getType() {
        return new Bool();
    }
}
