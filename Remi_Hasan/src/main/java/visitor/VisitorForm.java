package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import model.Form;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class VisitorForm extends QLBaseVisitor<Form> {

    @Override
    public Form visitRoot(QLParser.RootContext ctx) {
        VisitorStatement visitorStatement = new VisitorStatement();

        List<Question> questions = new ArrayList<>();
        for (QLParser.StatementContext statementContext : ctx.block().statement()) {
            List<Question> blockQuestions = visitorStatement.visit(statementContext);
            questions.addAll(blockQuestions);
        }

        return new Form(ctx.IDENTIFIER().getText(), questions);
    }

}
