package org.uva.sea.ql.exceptions;

import org.uva.sea.ql.Errors;

public class StaticAnalysisError extends Exception {

    Errors errors;

    public StaticAnalysisError(Errors errors) {
        this.errors = errors;
    }

    @Override
    public String getMessage(){
        return errors.toString();
    }
}
