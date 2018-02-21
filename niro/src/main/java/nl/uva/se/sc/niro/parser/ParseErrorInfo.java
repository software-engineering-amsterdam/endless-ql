package nl.uva.se.sc.niro.parser;

import org.antlr.v4.runtime.RecognitionException;

import java.util.List;

public class ParseErrorInfo {
    private int line;
    private int column;
    private String offendingSymbol;
    private String errorMessage;
    private List<String> rules;
    private RecognitionException cause;

    public ParseErrorInfo(int line, int column, String offendingSymbol, String errorMessage, List<String> rules, RecognitionException cause) {
        this.line = line;
        this.column = column;
        this.offendingSymbol = offendingSymbol;
        this.errorMessage = errorMessage;
        this.rules = rules;
        this.cause = cause;
    }

    @Override
    public String toString() {
        return String.format("At line %d, column %d the following error occurred : %s", line, column, errorMessage);
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getOffendingSymbol() {
        return offendingSymbol;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

}
