package QL.QLVisitor;

import QL.ParseObjectsQL.*;
import QL.QLAntlrGen.QLBaseVisitor;
import QL.QLAntlrGen.QLParser;

import java.util.ArrayList;

public class FormVisitor extends QLBaseVisitor<Form> {
    private ExpressionTable expressionTable;

    public FormVisitor(ExpressionTable exprTable){
        this.expressionTable = exprTable;
    }

    @Override
    public Form visitHead(QLParser.HeadContext ctx) {
        int line = ctx.getStart().getLine();
        BlockVisitor blockVisitor = new BlockVisitor(expressionTable);
        ArrayList<Question> blockQuestions = blockVisitor.visitBlock(ctx.block());

        return new Form(ctx.IDENTIFIER().getText(), blockQuestions, expressionTable, line);
    }
}
