package qls.builder;

import qls.antlr.QLSBaseVisitor;
import qls.antlr.QLSParser;
import qls.model.statement.Section;
import qls.model.statement.Statement;

import java.util.ArrayList;
import java.util.List;

public class SectionBuilder extends QLSBaseVisitor<Section> {

    @Override
    public Section visitSection(QLSParser.SectionContext ctx) {
        String identifier = ctx.title.getText();

        // Strip quotes surrounding string
        identifier = identifier.substring(1, identifier.length() - 1);

        // Get all statements inside this section
        List<Statement> statements = new ArrayList<>();
        StatementBuilder statementBuilder = new StatementBuilder();
        for (QLSParser.StatementContext statementContext : ctx.statement()) {
            statements.add(statementBuilder.visit(statementContext));
        }

        Section section = new Section(identifier, statements);
        section.setToken(ctx.getStart());
        return section;
    }
}
