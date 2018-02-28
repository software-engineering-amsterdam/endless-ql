package ql.evaluator.value;

import ql.ast.type.Type;
import ql.visitors.interfaces.ValueVisitor;

public class Undefined extends Value<String> {

    private final String value;
    
    public Undefined() { 
        this.value = null;
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
        return new ql.ast.type.Undefined();
    }
    
    @Override
    public Value<?> accept(ValueVisitor visitor) {
        return visitor.visit(this);
    }
}
