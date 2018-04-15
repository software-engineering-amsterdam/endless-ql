package QL.QLBuilder;

import QL.AST.*;
import QL.AST.Question;
import QL.QLAntlrGen.QLBaseVisitor;
import QL.QLAntlrGen.QLParser;

import java.util.ArrayList;

public class FormVisitor extends QLBaseVisitor<Form> {
    @Override
    public Form visitHead(QLParser.HeadContext ctx) {
        int line = ctx.getStart().getLine();
        BlockVisitor blockVisitor = new BlockVisitor();
        ArrayList<Question> blockQuestions = blockVisitor.visitBlock(ctx.block());

        return new Form(ctx.IDENTIFIER().getText(), blockQuestions, line);
    }
}
