package ql.exceptions;

import ql.ast.expression.Identifier;

public class DuplicateIdentifier extends QLException {

    public DuplicateIdentifier(Identifier id) {
        
        message     = "Duplicate identifier ["+id.getName()+"]";
        location    = id.getLocation();
    }
}
