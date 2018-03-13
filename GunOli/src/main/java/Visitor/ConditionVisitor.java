package Visitor;

import ParseObjectsQL.Block;
import ParseObjectsQL.Condition;
import ParseObjectsQL.Expressions.Expression;
import QLAntlrGen.QLBaseVisitor;
import QLAntlrGen.QLParser;

public class ConditionVisitor extends QLBaseVisitor<Condition>{
    private ExpressionTable expressionTable;

    public ConditionVisitor(ExpressionTable exprTable){
        super();
        this.expressionTable = exprTable;
    }

    @Override
    public Condition visitCondition(QLParser.ConditionContext ctx){
        ExpressionVisitor expressionVisitor = new ExpressionVisitor(expressionTable);
        BlockVisitor blockVisitor = new BlockVisitor(expressionTable);

        QLParser.ExpressionContext expressionCtx = ctx.expression();
        QLParser.BlockContext blockCtx = ctx.block();

        Expression condition = expressionVisitor.visit(expressionCtx);
        Block block = blockVisitor.visitBlock(blockCtx);
        return new Condition(condition, block);
    }
}

