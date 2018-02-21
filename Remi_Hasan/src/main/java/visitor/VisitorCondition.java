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

        ArrayList<Statement> elements = new ArrayList<>();
        VisitorStatement visitorStatement = new VisitorStatement();
        for (QLParser.StatementContext statementContext : ctx.block().statement()) {
            Statement statement = visitorStatement.visit(statementContext);
            elements.add(statement);
        }

        return new Condition(expression, elements);
    }

}
