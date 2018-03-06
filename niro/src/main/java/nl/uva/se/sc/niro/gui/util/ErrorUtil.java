package nl.uva.se.sc.niro.gui.util;

import nl.uva.se.sc.niro.parser.ParseErrorInfo;

import java.util.List;

public class ErrorUtil {
    public static String toString(List<ParseErrorInfo> parseErrors) {
        StringBuilder message = new StringBuilder();
        for (ParseErrorInfo errorInfo : parseErrors) {
            message.append(errorInfo.toString());
            message.append("\n");
        }
        return message.toString();
    }
}
