package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import expression.Expression;
import expression.variable.ExpressionVariableBoolean;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class VisitorStatement extends QLBaseVisitor<List<Question>> {

    private final Expression condition;
    public VisitorStatement(){
        this.condition = new ExpressionVariableBoolean(true);
    }

    public VisitorStatement(Expression condition){
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
