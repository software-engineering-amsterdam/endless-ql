package ql.visitor;

import ql.antlr.QLBaseVisitor;
import ql.antlr.QLParser;
import ql.model.statement.Question;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;

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
            return new Question(ctx.getStart(), questionType, questionName, questionText, defaultAnswer);
        }

        return new Question(ctx.getStart(), questionType, questionName, questionText);
    }

    private Expression getDefaultAnswer(QLParser.ExpressionContext expressionContext) {
        VisitorExpression visitorExpression = new VisitorExpression();
        return visitorExpression.visit(expressionContext);
    }

}