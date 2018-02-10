package ql.ast.statement;

import ql.ast.Identifier;
import ql.ast.type.Type;

public class AnswerableQuestion extends Question {
    
    private String label;
    private Identifier identifier;
    private Type type;
    
    public AnswerableQuestion(String label, Identifier identifier, Type type) {
        this.label = label;
        this.identifier = identifier;
        this.type = type;
    }
    
    
}
