package org.uva.sea.ql.exceptions;

import org.uva.sea.ql.staticAnalysis.Messages;

public class StaticAnalysisError extends Exception {

    Messages errors;

    public StaticAnalysisError(Messages errors) {
        this.errors = errors;
    }

    @Override
    public String getMessage(){
        return errors.toString();
    }
}
