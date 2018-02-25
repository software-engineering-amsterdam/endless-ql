package ql.ast.expression;

import ql.ast.type.Type;
import ql.value.Int;
import ql.visitors.interfaces.ExpressionVisitor;

public class IntLiteral extends Literal {

    private Int value;
    
    public IntLiteral() { 
        this.value = new Int();
    }
    
    public IntLiteral(String value) { 
        this.value = new Int(value);
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Int();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Int getValue() {
        return value;
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }
}
