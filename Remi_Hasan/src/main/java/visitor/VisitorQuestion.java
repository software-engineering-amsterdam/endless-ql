package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import expression.Expression;
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

        // Remove quotes surrounding the string
        questionText = questionText.substring(1, questionText.length() - 1);

        QLParser.QuestionTypeContext questionTypeContext = ctx.questionType();
        ReturnType questionType = ReturnType.valueOf(questionTypeContext.type().getText().toUpperCase());

        // Check whether answer can be filled in by user, or is based on an expression
        boolean isEditable = ctx.questionType().expression() == null;
        Expression defaultAnswer = getDefaultAnswer(ctx.questionType(), isEditable);

        return new Question(questionType, questionName, questionText, defaultAnswer, isEditable, this.condition);
    }

    private Expression getDefaultAnswer(QLParser.QuestionTypeContext questionType, boolean isEditable) {
        // If answer can be filled in by user, create empty (undefined) expression of correct type (for type checking)
        if(isEditable) {
            return new ExpressionVariableUndefined(ReturnType.valueOf(questionType.type().getText().toUpperCase()));
        }

        VisitorExpression visitorExpression = new VisitorExpression();
        return visitorExpression.visit(questionType.expression());
    }

}