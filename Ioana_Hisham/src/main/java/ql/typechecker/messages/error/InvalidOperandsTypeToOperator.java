package ql.typechecker.messages.error;

import java.text.MessageFormat;

public class InvalidOperandsTypeToOperator extends Error {
    private static final String MESSAGE = "***ERROR*** Invalid operand type at line {0}: to operation: {1}";

    public InvalidOperandsTypeToOperator(int lineNumber, String message) {
        super(MessageFormat.format(MESSAGE, Integer.toString(lineNumber), message));
    }
}
