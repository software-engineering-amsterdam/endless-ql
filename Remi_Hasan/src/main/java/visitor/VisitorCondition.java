package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import expression.Expression;
import expression.ReturnType;
import expression.binary.ExpressionLogicalAnd;
import expression.unary.ExpressionUnaryNot;
import model.Question;

import java.util.ArrayList;
import java.util.List;

public class VisitorCondition extends QLBaseVisitor<List<Question>> {

    private final Expression condition;

    VisitorCondition(Expression condition) {
        this.condition = condition;
    }

    @Override
    public List<Question> visitCondition(QLParser.ConditionContext ctx) {
        VisitorExpression visitorExpression = new VisitorExpression();
        Expression expression = visitorExpression.visit(ctx.expression());

        // Chain nested conditional statements
        Expression trueExpression = new ExpressionLogicalAnd(this.condition, expression);

        if(expression.getReturnType() != ReturnType.BOOLEAN) {
            throw new UnsupportedOperationException("Condition expression not of type boolean");
        }

        List<Question> questions = new ArrayList<>();

        VisitorStatement visitorStatementTrue = new VisitorStatement(trueExpression);
        for (QLParser.StatementContext statementContext : ctx.conditionTrueBlock.statement()) {
            List<Question> trueQuestions = visitorStatementTrue.visit(statementContext);
            questions.addAll(trueQuestions);
        }

        // Else block, so negate condition
        Expression falseExpression = new ExpressionLogicalAnd(new ExpressionUnaryNot(expression), this.condition);

        VisitorStatement visitorStatementFalse = new VisitorStatement(falseExpression);
        if(ctx.conditionFalseBlock != null){
            for (QLParser.StatementContext statementContext : ctx.conditionFalseBlock.statement()) {
                List<Question> falseQuestions = visitorStatementFalse.visit(statementContext);
                questions.addAll(falseQuestions);
            }
        }

        return questions;
    }

}