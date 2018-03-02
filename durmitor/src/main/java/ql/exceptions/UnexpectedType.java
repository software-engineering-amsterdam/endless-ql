package ql.exceptions;

import ql.ast.type.Type;

public class UnexpectedType extends QLException {

    public UnexpectedType(Type expected, Type actual) {
        
        message     = "Expected ["+expected+"] but got ["+actual+"]";
        location    = expected.getLocation();
    }
}
