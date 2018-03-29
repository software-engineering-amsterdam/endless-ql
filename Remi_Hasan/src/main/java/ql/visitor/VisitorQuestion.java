package ql.visitor;

import ql.antlr.QLBaseVisitor;
import ql.antlr.QLParser;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.statement.Question;

public class VisitorQuestion extends QLBaseVisitor<Question> {

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        String questionName = ctx.identifier.getText();
        String questionText = ctx.label.getText();

        // Remove quotes surrounding the string
        questionText = questionText.substring(1, questionText.length() - 1);

        ReturnType questionType = ReturnType.valueOf(ctx.type().getText().toUpperCase());

        // Check whether answer can be filled in by user, or is computed
        if (ctx.expression() != null) {
            Expression defaultAnswer = getDefaultAnswer(ctx.expression());
            Question question = new Question(questionType, questionName, questionText, defaultAnswer);
            question.setToken(ctx.getStart());
            return question;
        }

        Question question = new Question(questionType, questionName, questionText);
        question.setToken(ctx.getStart());
        return question;
    }

    private Expression getDefaultAnswer(QLParser.ExpressionContext expressionContext) {
        VisitorExpression visitorExpression = new VisitorExpression();
        return visitorExpression.visit(expressionContext);
    }

}