package ql.ast.expression;

import ql.ast.type.Type;
import ql.value.Str;
import ql.visitors.interfaces.ExpressionVisitor;

public class StrLiteral extends Literal {

    private Str value;
    
    public StrLiteral() { 
        this.value = new Str();
    }
    
    public StrLiteral(String value) { 
        this.value = new Str(value);
    }

    @Override
    public Type getType() {
        return new ql.ast.type.Str();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Str getValue() {
        return value;
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }
}
