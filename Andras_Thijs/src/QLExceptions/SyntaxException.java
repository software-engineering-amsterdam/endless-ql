package QLExceptions;

import Nodes.ASTNode;

/**
 * A basic SyntaxException which can hold a message and the ASTNode in which the error is found.
 */
public class SyntaxException extends Exception {
    private String message = "Syntax error";
    private ASTNode node;

    public SyntaxException() {}

    public SyntaxException(String message, ASTNode node) {
        this.message = message;
        this.node = node;
    }
}