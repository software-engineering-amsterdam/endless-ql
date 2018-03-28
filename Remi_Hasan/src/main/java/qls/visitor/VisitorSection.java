package qls.visitor;

import qls.antlr.QLSBaseVisitor;
import qls.antlr.QLSParser;
import qls.model.DefaultStyle;
import qls.model.Section;
import qls.model.Statement;

import java.util.ArrayList;
import java.util.List;

public class VisitorSection extends QLSBaseVisitor<Section> {

    @Override
    public Section visitSection(QLSParser.SectionContext ctx) {
        String identifier = ctx.title.getText();

        // Strip quotes surrounding string
        identifier = identifier.substring(1, identifier.length() - 1);

        // Get all statements inside this section
        List<Statement> statements = new ArrayList<>();
        VisitorStatement visitorStatement = new VisitorStatement();
        for (QLSParser.StatementContext statementContext : ctx.statement()) {
            statements.add(visitorStatement.visit(statementContext));
        }

        return new Section(ctx.getStart(), identifier, statements);
    }
}
