package ql.typechecker.messages.error;

import java.text.MessageFormat;

public class UndefinedReference extends Error {
    private static final String MESSAGE = "***ERROR*** Undefined question reference at line {0}: Reference: {1}";

    public UndefinedReference(int lineNumber, String message) {
        super(MessageFormat.format(MESSAGE, Integer.toString(lineNumber), message));
    }
}
