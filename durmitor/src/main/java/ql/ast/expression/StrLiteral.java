package ql.ast.expression;

import ql.ast.type.Str;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;
import ql.visitors.interfaces.LiteralVisitable;
import ql.visitors.interfaces.LiteralVisitor;

public class StrLiteral extends Literal<String> implements LiteralVisitable {

    private String value;
    
    public StrLiteral() { 
        this.value = "";
    }
    
    public StrLiteral(String value) { 
        this.value = value;
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
    public Type getType() {
        return new Str();
    }
    
    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Literal<?> accept(LiteralVisitor visitor) {
        System.out.println(this+".accept("+visitor+")");
        return visitor.visit(this);
    }
}
