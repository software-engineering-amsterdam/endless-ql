package ql.ast;

import ql.ast.expressions.literals.Identifier;

public class Form extends Node {

    private final Identifier identifier;
    private final Body body;

    public Form(int lineNumber, Identifier identifier, Body body) {
        super(lineNumber);
        this.identifier = identifier;
        this.body = body;
    }
}
