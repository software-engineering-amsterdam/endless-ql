package ql.ast;

public class Form extends Node {

    // todo: After implementing the correct QL type,
    // replace field type with the actual QL type
    private final String identifier;
    // todo: After implementing the correct QL type,
    // replace field type with the actual QL type
    private final String body;

    //todo: After implementing the correct QL type,
    // replace argument types(identifier and body)  with the actual QL type
    public Form(String identifier, String body, int lineNumber) {
        super(lineNumber);
        this.identifier = identifier;
        this.body = body;
    }
}
