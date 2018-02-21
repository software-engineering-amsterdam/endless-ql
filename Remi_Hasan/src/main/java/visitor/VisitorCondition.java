package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import expression.Expression;
import model.Condition;
import model.Statement;

import java.util.ArrayList;

public class VisitorCondition extends QLBaseVisitor<Condition> {

    @Override
    public Condition visitCondition(QLParser.ConditionContext ctx) {
        VisitorExpression visitorExpression = new VisitorExpression();
        Expression expression = visitorExpression.visit(ctx.expression());

        // Visit all statements in the conditional body
        ArrayList<Statement> statements = new ArrayList<>();
        VisitorStatement visitorStatement = new VisitorStatement();
        for (QLParser.StatementContext statementContext : ctx.block().statement()) {
            Statement statement = visitorStatement.visit(statementContext);
            statements.add(statement);
        }

        return new Condition(expression, statements);
    }

}
