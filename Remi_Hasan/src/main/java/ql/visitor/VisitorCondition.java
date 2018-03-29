package ql.visitor;

import ql.antlr.QLBaseVisitor;
import ql.antlr.QLParser;
import ql.model.statement.IfBlock;
import ql.model.statement.IfElseBlock;
import ql.model.statement.Statement;
import ql.model.expression.Expression;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.List;

public class VisitorCondition extends QLBaseVisitor<Statement> {

    @Override
    public Statement visitCondition(QLParser.ConditionContext ctx) {
        // Get the if condition
        VisitorExpression visitorExpression = new VisitorExpression();
        Expression condition = visitorExpression.visit(ctx.expression());

        VisitorStatement visitorStatement = new VisitorStatement();

        // Get all statements in the true block
        List<Statement> trueStatements = new ArrayList<>();
        for(QLParser.StatementContext statementContext : ctx.conditionTrueBlock.statement()) {
            trueStatements.add(visitorStatement.visit(statementContext));
        }

        if (ctx.conditionFalseBlock == null) {
            Statement block = new IfBlock(condition, trueStatements);
            block.setToken(ctx.getStart());
            return block;
        }

        // Get all statements in the false block
        List<Statement> falseStatements = new ArrayList<>();
        for(QLParser.StatementContext statementContext : ctx.conditionFalseBlock.statement()) {
            falseStatements.add(visitorStatement.visit(statementContext));
        }

        Statement block = new IfElseBlock(condition, trueStatements, falseStatements);
        block.setToken(ctx.getStart());
        return block;
    }

}