package ql.ast.statements;

import ql.ast.expressions.literals.Identifier;
import ql.ast.expressions.literals.String;
import ql.types.Type;

public class Question extends Statement {

    private String description;
    private Identifier identifier;
    private Type type;

    public Question(int lineNumber, String description, Identifier identifier, Type type) {
        super(lineNumber);
    }

    public String getDescription() {
        return description;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Type getType() {
        return type;
    }
}
