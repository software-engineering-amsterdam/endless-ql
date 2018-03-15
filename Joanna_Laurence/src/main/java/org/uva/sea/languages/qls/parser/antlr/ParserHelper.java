package org.uva.sea.languages.qls.parser.antlr;

public class ParserHelper {
    public static String removeQuotes(String string) {
        return string.substring(1, string.length() - 2);
    }
}
