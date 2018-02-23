package ql.ast.expression;

import java.util.Map;

import ql.ast.type.Type;
import ql.ast.type.Undefined;

public abstract class Primary extends Expression {
    
    public abstract String toString();
    
    public Type getType()
    {
        return new Undefined();
    }
    
    public Type getType(Map<String,Type> symbolTable) {
        
        if(this.isIdentifier())
        {
            return symbolTable.getOrDefault(((Identifier) this).getName(), new Undefined());
        }
        
        return getType();
    }
    
    @Override
    public boolean isPrimary() {
        return true;
    }
}
