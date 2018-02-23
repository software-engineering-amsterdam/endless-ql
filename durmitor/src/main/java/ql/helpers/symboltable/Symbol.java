package ql.helpers.symboltable;

import ql.ast.expression.Identifier;
import ql.ast.type.Type;

public class Symbol {

    private Identifier id;
    private Type type;
    
    public Symbol(Identifier id, Type type) {
        
        this.id         = id;
        this.type       = type;
    }
    
    public Identifier getIdentifier() {
        return id;
    }
    
    public Type getType() {
        return type;
    }
    
    public boolean equals(Symbol symbol) {
        
        return  id.getName().equals(symbol.getIdentifier().getName()) &&
                type.equals(symbol.getType());
    }
    
}
