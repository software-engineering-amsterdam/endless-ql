package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import expression.Expression;
import expression.ExpressionFactory;
import model.Question;

public class VisitorQuestion extends QLBaseVisitor<Question> {

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {

        String questionName = ctx.identifier().getText();
        String questionText = ctx.questionString().getText();
        // remove quotes from text
        questionText = questionText.substring(1, questionText.length() - 1);

        Expression answer = getStandardValue(ctx.questionType());
        return new Question(questionName, questionText, answer);
    }

    private Expression getStandardValue(QLParser.QuestionTypeContext questionType) {
        if (questionType.expression() != null) {
            VisitorExpression visitorExpression = new VisitorExpression();
            return visitorExpression.visit(questionType.expression());
        }

        return ExpressionFactory.createExpression(questionType.type().getText());
    }

}
