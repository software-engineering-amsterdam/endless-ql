package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import model.Condition;
import model.LookupTable;
import model.Question;
import model.Statement;

public class VisitorStatement extends QLBaseVisitor<Statement> {

    @Override
    public Condition visitCondition(QLParser.ConditionContext ctx) {
        VisitorCondition visitorCondition = new VisitorCondition();
        return visitorCondition.visitCondition(ctx);
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        VisitorQuestion visitorQuestion = new VisitorQuestion();
        Question question = visitorQuestion.visitQuestion(ctx);

        LookupTable lookupTable = LookupTable.getInstance();
        lookupTable.insert(question);

        return question;
    }

}
