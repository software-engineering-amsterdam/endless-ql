package main.org.uva.ql.app;

import generated.org.uva.ql.parser.QLBaseVisitor;
import generated.org.uva.ql.parser.QLParser;

public class ParseTreeVisitor extends QLBaseVisitor {

    public Form visitForm(QLParser.FormContext ctx) {
        System.out.println(String.format("Visit Form: %s", ctx.id.getText()));

        visitChildren(ctx);
        return new Form("Form");
    }

    public Form visitQuestion(QLParser.QuestionContext ctx) {
        System.out.println(String.format("Visit Question: %s", ctx.text.getText()));

        visitChildren(ctx);
        return new Form("Form");
    }
}
