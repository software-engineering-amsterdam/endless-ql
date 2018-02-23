package ql.ast.expression;

import ql.ast.type.Str;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;

public class StrLiteral extends Literal<String> {

    private String value;
    
    public StrLiteral() { 
        this.value = "";
    }
    
    public StrLiteral(String value) { 
        this.value = value;
    }

    @Override
    public Type getType() {
        return new Str();
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }
}
