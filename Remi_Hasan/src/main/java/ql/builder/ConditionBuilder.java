package ql.builder;

import ql.antlr.QLBaseVisitor;
import ql.antlr.QLParser;
import ql.model.expression.Expression;
import ql.model.statement.IfBlock;
import ql.model.statement.IfElseBlock;
import ql.model.statement.Statement;

import java.util.ArrayList;
import java.util.List;

public class ConditionBuilder extends QLBaseVisitor<Statement> {

    @Override
    public Statement visitCondition(QLParser.ConditionContext ctx) {
        // Get the if condition
        ExpressionBuilder expressionBuilder = new ExpressionBuilder();
        Expression condition = expressionBuilder.visit(ctx.expression());

        StatementBuilder statementBuilder = new StatementBuilder();

        // Get all statements in the true block
        List<Statement> trueStatements = new ArrayList<>();
        for (QLParser.StatementContext statementContext : ctx.conditionTrueBlock.statement()) {
            trueStatements.add(statementBuilder.visit(statementContext));
        }

        if (ctx.conditionFalseBlock == null) {
            Statement block = new IfBlock(condition, trueStatements);
            block.setToken(ctx.getStart());
            return block;
        }

        // Get all statements in the false block
        List<Statement> falseStatements = new ArrayList<>();
        for (QLParser.StatementContext statementContext : ctx.conditionFalseBlock.statement()) {
            falseStatements.add(statementBuilder.visit(statementContext));
        }

        Statement block = new IfElseBlock(condition, trueStatements, falseStatements);
        block.setToken(ctx.getStart());
        return block;
    }

}