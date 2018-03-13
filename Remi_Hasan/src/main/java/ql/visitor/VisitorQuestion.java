package ql.visitor;

import ql.parser.QLBaseVisitor;
import ql.parser.QLParser;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableUndefined;
import ql.model.Question;

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

        // Check whether answer can be filled in by user, or is based on an value
        boolean isComputed = ctx.questionType().expression() != null;
        Expression defaultAnswer = getDefaultAnswer(ctx.questionType(), isComputed);

        return new Question(ctx.getStart(),
                questionType, questionName, questionText, defaultAnswer, isComputed, this.condition);
    }

    private Expression getDefaultAnswer(QLParser.QuestionTypeContext questionType, boolean isComputed) {
        // If answer can be filled in by user, create empty (undefined) value of correct type (for type checking)
        if (!isComputed) {
            return new ExpressionVariableUndefined(questionType.getStart(),
                    ReturnType.valueOf(questionType.type().getText().toUpperCase()));
        }

        VisitorExpression visitorExpression = new VisitorExpression();
        return visitorExpression.visit(questionType.expression());
    }

}