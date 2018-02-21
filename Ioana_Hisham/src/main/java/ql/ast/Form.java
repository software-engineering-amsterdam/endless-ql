package ql.ast;

import ql.ast.expressions.literals.Identifier;
import ql.ast.statements.Statement;

import java.util.List;

public class Form extends Node {

    private final Identifier identifier;
    private final List<Statement> statements;

    public Form(int lineNumber, Identifier identifier, List<Statement> statements) {
        super(lineNumber);
        this.identifier = identifier;
        this.statements = statements;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public List<Statement> getStatements() {
        return statements;
    }
}
