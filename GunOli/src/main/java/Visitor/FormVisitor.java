package Visitor;

import ParseObjects.Question;
import ParseObjects.Condition;
import ParseObjects.Block;
import ParseObjects.Form;
import antlrGen.QLBaseVisitor;
import antlrGen.QLParser;

import java.util.ArrayList;

public class FormVisitor extends QLBaseVisitor<Form> {    @Override
    public Form visitHead(QLParser.HeadContext ctx) {
        BlockVisitor blockVisitor = new BlockVisitor();
        Block block = blockVisitor.visitBlock(ctx.block());
        return new Form(ctx.IDENTIFIER().getText(), block);
    }
}
