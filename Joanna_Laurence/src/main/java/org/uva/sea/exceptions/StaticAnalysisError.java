package org.uva.sea.exceptions;

import org.uva.sea.staticAnalysis.helpers.Messages;

public class StaticAnalysisError extends Exception {

    private Messages messages;

    public StaticAnalysisError(Messages errors) {
        this.messages = errors;
    }

    @Override
    public String getMessage() {
        return messages.toString();
    }
}
