package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import expression.Expression;
import expression.ReturnType;
import expression.unary.ExpressionUnaryNot;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class VisitorCondition extends QLBaseVisitor<List<Question>> {

    private final Expression condition;

    public VisitorCondition(Expression condition) {
        this.condition = condition;
    }

    @Override
    public List<Question> visitCondition(QLParser.ConditionContext ctx) {
        VisitorExpression visitorExpression = new VisitorExpression();
        Expression expression = visitorExpression.visit(ctx.expression());

        if(expression.getReturnType() != ReturnType.BOOLEAN) {
            throw new UnsupportedOperationException("Condition expression not of type boolean");
        }

        List<Question> questions = new ArrayList<>();

        VisitorStatement visitorStatementTrue = new VisitorStatement(expression);
        for (QLParser.StatementContext statementContext : ctx.conditionTrueBlock.statement()) {
            List<Question> trueQuestions = visitorStatementTrue.visit(statementContext);
            questions.addAll(trueQuestions);
        }

        VisitorStatement visitorStatementFalse = new VisitorStatement(new ExpressionUnaryNot(expression));
        if(ctx.conditionFalseBlock != null){
            for (QLParser.StatementContext statementContext : ctx.conditionFalseBlock.statement()) {
                List<Question> falseQuestions = visitorStatementFalse.visit(statementContext);
                questions.addAll(falseQuestions);
            }
        }

        return questions;
    }

}