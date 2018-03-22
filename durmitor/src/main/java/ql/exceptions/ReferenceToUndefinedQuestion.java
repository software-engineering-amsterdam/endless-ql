package ql.exceptions;

import ql.ast.expression.Identifier;

public class ReferenceToUndefinedQuestion extends QLException {

    public ReferenceToUndefinedQuestion(Identifier id) {
        
        message     = "Reference to undefined question [" + id.getName() + "]";
        location    = id.getLocation();
    }
}
