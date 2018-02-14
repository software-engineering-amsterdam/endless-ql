package ql.ast;

import ql.ast.expressions.literals.Identifier;

public class Form extends Node {

    private final Identifier identifier;
    // todo: After implementing the correct QL type,
    // replace field type with the actual QL type
    private final String body;

    //todo: After implementing the correct QL type,
    // replace argument types(identifier and body)  with the actual QL type
    public Form(int lineNumber, Identifier identifier, String body) {
        super(lineNumber);
        this.identifier = identifier;
        this.body = body;
    }
}
