import model.BlockElement;
import model.Condition;
import model.LookupTable;
import model.Question;

public class VisitorBlockElement extends QLBaseVisitor<BlockElement> {

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

    // TODO do we need this?
//    @Override
//    public ArrayList<Question> visitVariable(QLParser.VariableContext ctx) {
//        return new ArrayList<>();
//    }
}
