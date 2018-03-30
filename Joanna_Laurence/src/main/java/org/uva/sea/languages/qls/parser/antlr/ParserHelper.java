package org.uva.sea.languages.qls.parser.antlr;

class ParserHelper {
    public static String removeQuotes(String text) {
        return text.substring(1, text.length() - 1);
    }
}
