package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import model.Form;
import model.Statement;

import java.util.ArrayList;
import java.util.List;

public class VisitorForm extends QLBaseVisitor<Form> {

    @Override
    public Form visitRoot(QLParser.RootContext ctx) {
        VisitorStatement visitorStatement = new VisitorStatement();

        List<Statement> statements = new ArrayList<>();
        for (QLParser.StatementContext statementContext : ctx.block().statement()) {
            Statement statement = visitorStatement.visit(statementContext);
            statements.add(statement);
        }

        return new Form(ctx.IDENTIFIER().getText(), statements);
    }

}
