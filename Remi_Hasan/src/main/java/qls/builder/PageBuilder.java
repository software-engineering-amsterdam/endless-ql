package qls.builder;

import qls.antlr.QLSBaseVisitor;
import qls.antlr.QLSParser;
import qls.model.Page;
import qls.model.statement.Statement;

import java.util.ArrayList;
import java.util.List;

public class PageBuilder extends QLSBaseVisitor<Page> {

    @Override
    public Page visitPage(QLSParser.PageContext ctx) {
        // Get all statements inside this page
        List<Statement> statements = new ArrayList<>();
        StatementBuilder statementBuilder = new StatementBuilder();
        for (QLSParser.StatementContext statementContext : ctx.statement()) {
            statements.add(statementBuilder.visit(statementContext));
        }

        Page page = new Page(ctx.identifier.getText(), statements);
        page.setToken(ctx.getStart());
        return page;
    }
}