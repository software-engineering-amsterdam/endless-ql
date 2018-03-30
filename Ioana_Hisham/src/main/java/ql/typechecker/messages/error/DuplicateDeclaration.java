package ql.typechecker.messages.error;

import java.text.MessageFormat;

public class DuplicateDeclaration extends Error {
    private static final String MESSAGE = "***ERROR*** Duplicate question declaration at line {0}: Declaration: {1}";

    public DuplicateDeclaration(int lineNumber, String message) {
        super(MessageFormat.format(MESSAGE, Integer.toString(lineNumber), message));
    }
}
