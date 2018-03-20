package QLExceptions;

import Nodes.ASTNode;
import Nodes.Type;

/**
 * A basic Exception for Type errors.
 */
public class TypeException extends Exception {
    private ASTNode node;
    private Type expected;
    private Type received;

    public TypeException() {}

    public TypeException(ASTNode node) {
        this.node = node;
    }

    public TypeException(Type expected, Type received) {
        this.expected = expected;
        this.received = received;
    }

    public TypeException(ASTNode node, Type expected, Type received) {
        this.node = node;
        this.expected = expected;
        this.received = received;
    }

    @Override
    public String getMessage() {
        return "Types don't match";
    }

    public ASTNode getNode() {
        return node;
    }

    public Type getExpected() {
        return expected;
    }

    public Type getReceived() {
        return received;
    }
}
