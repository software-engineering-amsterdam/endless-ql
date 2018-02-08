import answer.Answer;
import answer.AnswerFactory;

public class VisitorQuestionType extends QLBaseVisitor<Answer> {
    @Override
    public Answer visitQuestionType(QLParser.QuestionTypeContext ctx) {

        Answer answer = AnswerFactory.createAnswer(ctx.type().getText());
        return answer;
    }
}
