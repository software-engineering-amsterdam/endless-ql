import java.util.ArrayList;
import java.util.stream.Collectors;

public class VisitorBlock extends QLBaseVisitor<Block> {

    @Override
    public Block visitBlock(QLParser.BlockContext ctx) {

        VisitorQuestion visitorQuestion = new VisitorQuestion();
        VisitorCondition visitorCondition = new VisitorCondition();

        // Visit all questions
        ArrayList<Question> questions =
                ctx
                        .question().stream()
                        .map(question -> visitorQuestion.visitQuestion(question))
                        .collect(Collectors.toCollection(ArrayList::new));

        // Visit all conditions
        ArrayList<Condition> conditions =
                ctx
                        .condition().stream()
                        .map(condition -> visitorCondition.visitCondition(condition))
                        .collect(Collectors.toCollection(ArrayList::new));

        return new Block(questions, conditions);
    }
}
