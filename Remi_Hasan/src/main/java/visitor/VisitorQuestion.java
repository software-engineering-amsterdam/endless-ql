package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import expression.*;
import expression.variable.ExpressionVariableBoolean;
import model.LookupTable;
import model.Question;

public class VisitorQuestion extends QLBaseVisitor<Question> {

    private final Expression condition;

    public VisitorQuestion(Expression condition) {
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

        Question question = new Question(questionType, questionName, questionText, defaultAnswer, this.condition);

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