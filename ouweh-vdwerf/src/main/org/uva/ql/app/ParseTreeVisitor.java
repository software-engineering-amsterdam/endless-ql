package main.org.uva.ql.app;

import generated.org.uva.ql.parser.QLBaseVisitor;
import generated.org.uva.ql.parser.QLParser;
import main.org.uva.ql.ast.Form;
import main.org.uva.ql.ast.TreeNode;

public class ParseTreeVisitor extends QLBaseVisitor {

    @Override
    public Form visitForm(QLParser.FormContext ctx) {
        System.out.println(String.format("Visit Form: %s", ctx.id.getText()));

        visitChildren(ctx);
        return new Form("Form");
    }

    @Override
    public TreeNode visitQuestion(QLParser.QuestionContext ctx) {
        System.out.println(String.format("Visit Question: %s", ctx.text.getText()));

        visitChildren(ctx);
        return new Form("Form");
    }

    @Override
    public TreeNode visitIfStatement(QLParser.IfStatementContext ctx) {
        System.out.println("Test");

        visitChildren(ctx);
        return new Form( "From");
    }
}
