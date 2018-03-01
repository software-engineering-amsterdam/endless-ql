package Visitor;

import ParseObjects.Block;
import ParseObjects.Condition;
import ParseObjects.Expressions.Expression;
import antlrGen.QLBaseVisitor;
import antlrGen.QLParser;

public class ConditionVisitor extends QLBaseVisitor<Condition>{
    @Override
    public Condition visitCondition(QLParser.ConditionContext ctx){
        ExpressionVisitor expressionVisitor = new ExpressionVisitor();
        BlockVisitor blockVisitor = new BlockVisitor();

        QLParser.ExpressionContext expressionCtx = ctx.expression();
        QLParser.BlockContext blockCtx = ctx.block();

        Expression condition = expressionVisitor.visit(expressionCtx);
        Block block = blockVisitor.visitBlock(blockCtx);
        return new Condition(condition, block);
    }
}

