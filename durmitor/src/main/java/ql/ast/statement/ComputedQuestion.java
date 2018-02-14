package ql.ast.statement;

import ql.ast.Identifier;
import ql.ast.expression.Expression;
import ql.ast.type.Type;

public class ComputedQuestion extends Question {
    
    private String label;
    private Identifier identifier;
    private Type type;
    private Expression expression;
    
    public ComputedQuestion(String label, Identifier identifier, Type type, Expression expression) {
        this.label = label;
        this.identifier = identifier;
        this.type = type;
        this.expression = expression;
    }

    
}
