package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import expression.Expression;
import expression.ExpressionFactory;
import model.LookupTable;
import model.Question;

public class VisitorQuestion extends QLBaseVisitor<Question> {

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        String questionName = ctx.identifier().getText();
        String questionText = ctx.questionString().getText();

        // remove quotes from text
        questionText = questionText.substring(1, questionText.length() - 1);

        Expression defaultAnswer = getDefaultAnswer(ctx.questionType());

        Question question = new Question(questionName, questionText, defaultAnswer);

        LookupTable lookupTable = LookupTable.getInstance();
        lookupTable.insert(question);

        return question;
    }

    private Expression getDefaultAnswer(QLParser.QuestionTypeContext questionType) {
        if (questionType.expression() != null) {
            VisitorExpression visitorExpression = new VisitorExpression();
            return visitorExpression.visit(questionType.expression());
        }

        // Create empty expression of question type
        return ExpressionFactory.createExpression(questionType.type().getText());
    }

}
