package main.org.uva.ql.app;

import generated.org.uva.ql.parser.QLBaseVisitor;
import generated.org.uva.ql.parser.QLParser;

public class ParseTreeVisitor extends QLBaseVisitor {

    public Form visitForm(QLParser.FormContext ctx) {
        visitChildren(ctx);
        System.out.println(String.format("Visit Form: %s", ctx.id.getText()));
        return new Form("Form");
    }

    public Form visitQuestion(QLParser.FormContext ctx) {
        visitChildren(ctx);
        System.out.println(String.format("Visit Question: %s", ctx.id.getText()));
        return new Form("Form");
    }


}
