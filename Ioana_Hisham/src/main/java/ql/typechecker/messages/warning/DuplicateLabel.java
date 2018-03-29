package ql.typechecker.messages.warning;

import java.text.MessageFormat;

public class DuplicateLabel extends Warning {
    private static final String MESSAGE = "***Warning*** Duplicate question label at line {0}: Label: {1}";

    public DuplicateLabel(int lineNumber, String message) {
        super(MessageFormat.format(MESSAGE, Integer.toString(lineNumber), message));
    }
}
