package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import expression.Expression;
import expression.ExpressionFactory;

public class VisitorQuestionType extends QLBaseVisitor<Expression> {

    @Override
    public Expression visitQuestionType(QLParser.QuestionTypeContext ctx) {
        if (ctx.expression() != null) {
            VisitorExpression visitorExpression = new VisitorExpression();
            System.out.println("a: " + visitorExpression.visit(ctx.expression()));
            return visitorExpression.visit(ctx.expression());
        } else {
            Expression answer = ExpressionFactory.createExpression(ctx.type().getText());
            return answer;
        }

    }
}
