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

    public TypeException(ASTNode node, Type expected, Type received) {
        this.node = node;
        this.expected = expected;
        this.received = received;
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
