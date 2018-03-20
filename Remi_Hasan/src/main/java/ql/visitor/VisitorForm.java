package ql.visitor;

import ql.parser.QLBaseVisitor;
import ql.parser.QLParser;
import ql.model.Form;
import ql.model.Question;

import java.util.ArrayList;
import java.util.List;

public class VisitorForm extends QLBaseVisitor<Form> {

    @Override
    public Form visitRoot(QLParser.RootContext ctx) {
        VisitorStatement visitorStatement = new VisitorStatement(ctx.getStart());

        List<Question> questions = new ArrayList<>();
        for (QLParser.StatementContext statementContext : ctx.block().statement()) {
            List<Question> blockQuestions = visitorStatement.visit(statementContext);
            questions.addAll(blockQuestions);
        }

        return new Form(ctx.getStart(), ctx.IDENTIFIER().getText(), questions);
    }

}
