package visitor;

import antlr.QLBaseVisitor;
import antlr.QLParser;
import expression.Expression;
import expression.ReturnType;
import model.Condition;
import model.Statement;

import java.util.ArrayList;
import java.util.List;

public class VisitorCondition extends QLBaseVisitor<Condition> {

    @Override
    public Condition visitCondition(QLParser.ConditionContext ctx) {
        VisitorExpression visitorExpression = new VisitorExpression();
        Expression expression = visitorExpression.visit(ctx.expression());

        if(expression.getReturnType() != ReturnType.BOOLEAN) {
            throw new UnsupportedOperationException("Condition expression not of type boolean");
        }

        // Visit all conditionTrueStatements in the conditional body
        List<Statement> conditionTrueStatements = new ArrayList<>();
        List<Statement> conditionFalseStatements = new ArrayList<>();
        VisitorStatement visitorStatement = new VisitorStatement();

        for (QLParser.StatementContext statementContext : ctx.conditionTrueBlock.statement()) {
            Statement statement = visitorStatement.visit(statementContext);
            conditionTrueStatements.add(statement);
        }

        if(ctx.conditionFalseBlock != null){
            for (QLParser.StatementContext statementContext : ctx.conditionFalseBlock.statement()) {
                Statement statement = visitorStatement.visit(statementContext);
                conditionFalseStatements.add(statement);
            }
        }

        return new Condition(expression, conditionTrueStatements, conditionFalseStatements);
    }

}