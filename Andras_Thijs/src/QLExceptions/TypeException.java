package QLExceptions;

import Nodes.Type;

/**
 * A basic Exception for Type errors.
 */
public class TypeException extends Exception {
    private Type expected;
    private Type received;

    public TypeException() {}

    public TypeException(Type expected, Type received) {
        this.expected = expected;
        this.received = received;
    }

    @Override
    public String getMessage() {
        return "Types don't match";
    }

    public Type getExpected() {
        return expected;
    }

    public Type getReceived() {
        return received;
    }
}
