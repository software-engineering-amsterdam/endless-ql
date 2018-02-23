package ql.ast.expression;

import ql.ast.type.Int;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;

public class IntLiteral extends Literal<Integer> {

    private int value;
    
    public IntLiteral() { 
        this.value = 0;
    }
    
    public IntLiteral(String value) { 
        this.value = Integer.parseInt(value);
    }

    public IntLiteral(int value) { 
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Integer getValue() {
        return value;
    }
    
    @Override
    public Type getType() {
        return new Int();
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
