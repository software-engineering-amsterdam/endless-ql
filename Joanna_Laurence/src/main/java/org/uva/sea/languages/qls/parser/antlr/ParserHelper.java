package org.uva.sea.languages.qls.parser.antlr;

final class ParserHelper {
    public static String removeQuotes(final String text) {
        return text.substring(1, text.length() - 1);
    }
}
