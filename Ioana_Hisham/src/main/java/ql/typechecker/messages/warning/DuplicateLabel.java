package ql.typechecker.messages.warning;

import java.text.MessageFormat;

public class DuplicateLabel extends Warning {
    private static final String WARNING = "***Warning*** Duplicate question label at line {0}: duplicate question label: {1}";

    DuplicateLabel(int lineNumber, String message) {
        super(MessageFormat.format(WARNING, Integer.toString(lineNumber), message));
    }
}
