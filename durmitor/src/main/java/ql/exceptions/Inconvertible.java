package ql.exceptions;

import ql.ast.type.Type;

public class Inconvertible extends QLException {

    public Inconvertible(Type to, Type from) {
        
        message     = "Cannot convert from ["+from+"] to ["+to+"]";
        location    = to.getLocation();
    }
}
