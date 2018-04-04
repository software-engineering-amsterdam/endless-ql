package ql.typechecker.messages.error;

import java.text.MessageFormat;

public class TypeMismatch extends Error {
    private static final String MESSAGE = "***ERROR*** Type mismatch at line {0}: Expected boolean but got: {1}";

    public TypeMismatch(int lineNumber, String message) {
        super(MessageFormat.format(MESSAGE, Integer.toString(lineNumber), message));
    }
}
