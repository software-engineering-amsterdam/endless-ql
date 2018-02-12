import expression.Expression;
import model.Question;

import java.util.ArrayList;

public class VisitorBlockElement extends QLBaseVisitor<ArrayList<Question>> {

    private final ArrayList<Expression> conditions;

    VisitorBlockElement(ArrayList<Expression> conditions){
        this.conditions = conditions;
    }

    @Override
    public ArrayList<Question> visitQuestion(QLParser.QuestionContext ctx) {
        VisitorQuestion visitorQuestion = new VisitorQuestion();
        Question question = visitorQuestion.visitQuestion(ctx);
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(question);
        return questions;
    }

    @Override
    public ArrayList<Question> visitCondition(QLParser.ConditionContext ctx) {
        VisitorCondition visitorCondition = new VisitorCondition(conditions);
        return visitorCondition.visitCondition(ctx);
    }
}
