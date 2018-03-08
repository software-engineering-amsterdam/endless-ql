package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import model.expression.Expression;
import model.expression.variable.ExpressionVariableBoolean;
import model.Question;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class VisitorStatement extends QLBaseVisitor<List<Question>> {

    private final Expression condition;

    VisitorStatement(Token start) {
        this.condition = new ExpressionVariableBoolean(start,true);
    }

    VisitorStatement(Expression condition) {
        this.condition = condition;
    }

    @Override
    public List<Question> visitCondition(QLParser.ConditionContext ctx) {
        VisitorCondition visitorCondition = new VisitorCondition(this.condition);
        return visitorCondition.visitCondition(ctx);
    }

    @Override
    public List<Question> visitQuestion(QLParser.QuestionContext ctx) {
        VisitorQuestion visitorQuestion = new VisitorQuestion(this.condition);
        Question question = visitorQuestion.visitQuestion(ctx);

        List<Question> questions = new ArrayList<>();
        questions.add(question);

        return questions;
    }

}
