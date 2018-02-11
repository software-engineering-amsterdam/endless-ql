import expression.ExpressionFactory;
import expression.Expression;

public class VisitorQuestionType extends QLBaseVisitor<Expression> {
    @Override
    public Expression visitQuestionType(QLParser.QuestionTypeContext ctx) {

        Expression answer = ExpressionFactory.createExpression(ctx.type().getText());
        return answer;
    }
}
