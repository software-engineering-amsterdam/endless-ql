package ql.ast.statement;

import ql.ast.expression.Identifier;

public abstract class Question extends Statement {
    
    protected String label;
    protected Identifier id;
    
    public Question(String label, Identifier id) { 
        this.label = label;
        this.id = id;
    }
    
    public String getLabel() {
        return label;
    }
    
    public Identifier getIdentifier() {
        return id;
    }
    
    public boolean equals(Question question) {
        return this.toString().equals(question.toString());
    }

    public boolean isAnswerable() {
        return false;
    }
}
