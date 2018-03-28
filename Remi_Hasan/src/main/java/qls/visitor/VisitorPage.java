package qls.visitor;

import qls.antlr.QLSBaseVisitor;
import qls.model.DefaultStyle;
import qls.model.Page;
import qls.model.Section;
import qls.antlr.QLSParser;
import qls.model.Statement;

import java.util.ArrayList;
import java.util.List;

public class VisitorPage extends QLSBaseVisitor<Page> {

    @Override
    public Page visitPage(QLSParser.PageContext ctx) {
        // Get all statements inside this page
        List<Statement> statements = new ArrayList<>();
        VisitorStatement visitorStatement = new VisitorStatement();
        for (QLSParser.StatementContext statementContext : ctx.statement()) {
            statements.add(visitorStatement.visit(statementContext));
        }

        return new Page(ctx.getStart(), ctx.identifier.getText(), statements);
    }
}