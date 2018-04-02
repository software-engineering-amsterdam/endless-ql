package ql.ast.statements;

import ql.ast.expressions.Identifier;
import ql.ast.expressions.literals.StringLiteral;
import ql.types.Type;
import ql.visitors.StatementVisitor;

public class Question extends Statement {

    private StringLiteral description;
    private Identifier identifier;
    private Type type;

    public Question(int lineNumber, StringLiteral description, Identifier identifier, Type type) {
        super(lineNumber);
        this.description = description;
        this.identifier = identifier;
        this.type = type;
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

    @Override
    public <T> T accept(StatementVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Question{" +
                "description=" + description +
                ", identifier=" + identifier +
                ", type=" + type +
                '}';
    }
}
