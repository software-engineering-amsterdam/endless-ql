package org.uva.sea.ql.parser.prettyPrinter;

import org.uva.sea.ql.parser.antlr.QLBaseListener;
import org.uva.sea.ql.parser.antlr.QLParser;
import org.uva.sea.ql.parser.elements.Question;

import java.util.Collections;

public class BasicPrettyPrinter extends QLBaseListener {

    /**
     * Tabs for the current line of code
     */
    private int tabs = 0;

    /**
     * Increase how many tabs are needed for the current line of code
     */
    private void incTab() {
        this.tabs += 1;
    }

    /**
     * Decrease how many tabs are needed for the current line of code
     */
    private void decTab() {
        this.tabs -= 1;
    }

    /**
     * Get tabs that are needed for the line of code
     */
    private String getTabs() {
        if(this.tabs <= 0)
            return "";
        return new String(new char[this.tabs]).replace("\0", "\t");
    }

    @Override
    public void enterForm(QLParser.FormContext context) {
        System.out.printf("%s%nform %s ", this.getTabs(), context.result.getName());
    }

    @Override
    public void exitForm(QLParser.FormContext context) {
    }

    @Override
    public void enterQuestion(QLParser.QuestionContext context) {
        //TODO: Use traversal to print sub elements
        Question question = context.result;
        System.out.printf("%s\"%s\" %s: %s", this.getTabs(),
                                                question.getLabel(),
                                                question.getVariable(),
                                                question.getType());
    }

    @Override
    public void exitQuestion(QLParser.QuestionContext context) {
        System.out.printf("%n");
    }

    @Override
    public void enterCondition(QLParser.ConditionContext context) {
        System.out.printf("%sif ", this.getTabs());
    }

    @Override
    public void exitCondition(QLParser.ConditionContext context) {
        System.out.printf("%n");
    }

    @Override
    public void enterStatements(QLParser.StatementsContext ctx) {
        System.out.printf("%s{%n", this.getTabs());
        this.incTab();
    }

    @Override
    public void exitStatements(QLParser.StatementsContext ctx) {
        this.decTab();
        System.out.printf("%n%s}%n", this.getTabs());
    }

    @Override
    public void enterExpression(QLParser.ExpressionContext ctx) {
        System.out.print("(EXPR)");
    }

    @Override
    public void exitExpression(QLParser.ExpressionContext ctx) {

    }
}
