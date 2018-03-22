package ql.exceptions;

import ql.ast.expression.Expression;

public class NonBooleanCondition extends QLException {

    public NonBooleanCondition(Expression condition) {
        
        message     = "Non-boolean condition ["+condition+"]";
        location    = condition.getLocation();
    }
}
