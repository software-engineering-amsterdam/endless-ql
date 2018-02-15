package ql.ast.statement;

import ql.ast.expression.Identifier;
import ql.ast.type.Type;

public abstract class Question extends Statement {
    
    protected String label;
    protected Identifier id;
    protected Type type;
    
    public Question(String label, Identifier id, Type type) { 
        this.label = label;
        this.id = id;
        this.type = type;
    }
}
