package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import expression.Expression;
import model.Question;

public class VisitorQuestion extends QLBaseVisitor<Question> {

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        VisitorQuestionType visitorQuestionType = new VisitorQuestionType();

        String questionName = ctx.identifier().getText();
        String questionText = ctx.questionString().getText();
        // remove quotes from text
        questionText = questionText.substring(1, questionText.length() - 1);
        Expression answer = visitorQuestionType.visit(ctx.questionType());

        return new Question(questionName, questionText, answer);
    }

}
