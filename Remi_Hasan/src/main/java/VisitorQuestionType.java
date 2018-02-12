import expression.ExpressionFactory;
import expression.Expression;

public class VisitorQuestionType extends QLBaseVisitor<Expression> {
    @Override
    public Expression visitQuestionType(QLParser.QuestionTypeContext ctx) {

//        final Expression expression;
//        if(ctx.expression()){
//            //VisitorExpression visitorExpression = new VisitorExpression();
//        }
        Object type = ctx.type();
        Object expression = ctx.expression();
        System.out.println(ctx.type() + " " + ctx.expression());

        if(ctx.expression() != null){
            VisitorExpression visitorExpression = new VisitorExpression();
            System.out.println("a: " + visitorExpression.visit(ctx.expression()));
            return visitorExpression.visit(ctx.expression());
        } else {
            Expression answer = ExpressionFactory.createExpression(ctx.type().getText());
            return answer;
        }

    }
}
