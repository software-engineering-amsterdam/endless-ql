package qlviz.analyzer;

import org.antlr.v4.runtime.ParserRuleContext;

public class MismatchedTypeResult implements AnalysisResult {

    private final ParserRuleContext context;

    public MismatchedTypeResult(ParserRuleContext context) {
        this.context = context;
    }

    @Override
    public String getDescription() {
        return "Mismatched expression type on line " + context.getStart().getLine()
                + " in expression \"" + context.getText() + "\"";
    }

    @Override
    public Severity getSeverity() {
        return Severity.Error;
    }
}
