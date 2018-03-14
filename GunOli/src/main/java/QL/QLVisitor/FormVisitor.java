package QL.QLVisitor;

import QL.ParseObjectsQL.*;
import QL.QLAntlrGen.QLBaseVisitor;
import QL.QLAntlrGen.QLParser;

public class FormVisitor extends QLBaseVisitor<Form> {
    private ExpressionTable expressionTable;

    public FormVisitor(ExpressionTable exprTable){
        this.expressionTable = exprTable;
    }

    @Override
    public Form visitHead(QLParser.HeadContext ctx) {
        BlockVisitor blockVisitor = new BlockVisitor(expressionTable);
        Block block = blockVisitor.visitBlock(ctx.block());

        return new Form(ctx.IDENTIFIER().getText(), block, expressionTable);
    }
}
