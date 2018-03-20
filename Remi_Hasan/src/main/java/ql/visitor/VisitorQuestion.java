package ql.visitor;

import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.parser.QLBaseVisitor;
import ql.parser.QLParser;

public class VisitorQuestion extends QLBaseVisitor<Question> {

    private final Expression condition;

    VisitorQuestion(Expression condition) {
        this.condition = condition;
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        String questionName = ctx.identifier().getText();
        String questionText = ctx.questionString().getText();

        // Remove quotes surrounding the string
        questionText = questionText.substring(1, questionText.length() - 1);

        QLParser.QuestionTypeContext questionTypeContext = ctx.questionType();
        ReturnType questionType = ReturnType.valueOf(questionTypeContext.type().getText().toUpperCase());

        // Check whether answer can be filled in by user, or is computed
        if (ctx.questionType().expression() != null) {
            Expression defaultAnswer = getDefaultAnswer(ctx.questionType());
            return new Question(ctx.getStart(), questionType, questionName, questionText, defaultAnswer, this.condition);
        } else {
            return new Question(ctx.getStart(), questionType, questionName, questionText, this.condition);
        }
    }

    private Expression getDefaultAnswer(QLParser.QuestionTypeContext questionType) {
        VisitorExpression visitorExpression = new VisitorExpression();
        return visitorExpression.visit(questionType.expression());
    }

}