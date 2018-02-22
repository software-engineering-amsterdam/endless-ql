package ql.ast.statements;

import ql.ast.expressions.literals.Identifier;
import ql.ast.expressions.literals.StringLiteral;
import ql.types.Type;

public class Question extends Statement {

    private StringLiteral description;
    private Identifier identifier;
    private Type type;

    public Question(int lineNumber, StringLiteral description, Identifier identifier, Type type) {
        super(lineNumber);
    }

    public StringLiteral getDescription() {
        return description;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Type getType() {
        return type;
    }
}
