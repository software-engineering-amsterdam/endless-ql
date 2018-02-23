package ql.ast.expression;

import ql.ast.type.Str;
import ql.ast.type.Type;
import ql.value.Value;
import ql.visitors.interfaces.ExpressionVisitor;

public class StrLiteral extends Literal {

    private Value<String> value;
    
    public StrLiteral() { 
        this.value = new ql.value.Str();
    }
    
    public StrLiteral(String value) { 
        this.value = new ql.value.Str(value);
    }

    @Override
    public Type getType() {
        return new Str();
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
