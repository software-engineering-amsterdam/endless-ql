package Visitor;

import ParseObjects.*;
import antlrGen.QLBaseVisitor;
import antlrGen.QLParser;

public class FormVisitor extends QLBaseVisitor<Form> {
    private ExpressionTable expressionTable;

    public FormVisitor(ExpressionTable exprTable){
        super();
        this.expressionTable = exprTable;
    }

    @Override
    public Form visitHead(QLParser.HeadContext ctx) {
        BlockVisitor blockVisitor = new BlockVisitor(expressionTable);
        Block block = blockVisitor.visitBlock(ctx.block());

        return new Form(ctx.IDENTIFIER().getText(), block);
    }
}
