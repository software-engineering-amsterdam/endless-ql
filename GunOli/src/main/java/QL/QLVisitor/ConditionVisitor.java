package QL.QLVisitor;

import QL.ParseObjectsQL.Block;
import QL.ParseObjectsQL.Condition;
import QL.ParseObjectsQL.Expressions.Expression;
import QL.QLAntlrGen.QLBaseVisitor;
import QL.QLAntlrGen.QLParser;

public class ConditionVisitor extends QLBaseVisitor<Condition>{
    private ExpressionTable expressionTable;

    public ConditionVisitor(ExpressionTable exprTable){
        this.expressionTable = exprTable;
    }

    @Override
    public Condition visitCondition(QLParser.ConditionContext ctx){
        ExpressionVisitor expressionVisitor = new ExpressionVisitor(expressionTable);
        QLParser.ExpressionContext expressionCtx = ctx.expression();
        Expression condition = expressionVisitor.visit(expressionCtx);

        BlockVisitor blockVisitor = new BlockVisitor(expressionTable, condition);
        QLParser.BlockContext blockCtx = ctx.block();
        Block block = blockVisitor.visitBlock(blockCtx);
        return new Condition(condition, block);
    }
}

