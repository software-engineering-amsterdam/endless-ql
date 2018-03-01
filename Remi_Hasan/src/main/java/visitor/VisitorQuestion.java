package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import expression.Expression;
import expression.ExpressionFactory;
import expression.ReturnType;
import expression.variable.ExpressionVariableUndefined;
import model.Question;

public class VisitorQuestion extends QLBaseVisitor<Question> {

    private final Expression condition;

    VisitorQuestion(Expression condition) {
        this.condition = condition;
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        String questionName = ctx.identifier().getText();
        String questionText = ctx.questionString().getText();

        // remove quotes from text
        questionText = questionText.substring(1, questionText.length() - 1);

        QLParser.QuestionTypeContext questionTypeContext = ctx.questionType();
        ReturnType questionType = ReturnType.valueOf(questionTypeContext.type().getText().toUpperCase());
        Expression defaultAnswer = getDefaultAnswer(ctx.questionType());

        // Check whether question can be edited by user, or is based on an expression
        boolean isEditable = ctx.questionType().expression() == null;

        return new Question(questionType, questionName, questionText, defaultAnswer,
                isEditable, this.condition);
    }

    private Expression getDefaultAnswer(QLParser.QuestionTypeContext questionType) {
        if(questionType.expression() == null) {
            // TODO: preferably change to undefined, but that breaks type checking
            return ExpressionFactory.createExpression(questionType.type().getText());
        }

        VisitorExpression visitorExpression = new VisitorExpression();
        return visitorExpression.visit(questionType.expression());
    }

}