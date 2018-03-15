package QLExceptions;

import Nodes.Type;

/**
 * A basic Exception for Type errors.
 */
public class TypeException extends Exception {
    private String message = "Types don't match";
    private Type expected;
    private Type received;

    public TypeException() {}

    public TypeException(Type expected, Type received) {
        this.expected = expected;
        this.received = received;
    }
}
