package models.ast;

import models.ast.elements.ConditionBlock;
import models.ast.elements.Form;
import models.ast.elements.Block;
import grammar.QLBaseVisitor;
import grammar.QLParser;
import models.ast.elements.QuestionBlock;

public class ASTBuilder extends QLBaseVisitor {

    @Override
    public Form visitForm(QLParser.FormContext ctx) {

        Form form = new Form(ctx.id.getText());

        for (QLParser.BlockContext bctx: ctx.block()) {
            Block block = visitBlock(bctx);
            form.addBlock(block);
        }

        return form;
    }

    @Override
    public Block visitBlock(QLParser.BlockContext ctx) {

        if(ctx.ifBlock() != null) {
            return visitIfBlock(ctx.ifBlock());
        } else if (ctx.question() != null) {
            return visitQuestion(ctx.question());
        }

        return null;
    }

    @Override
    public ConditionBlock visitIfBlock(QLParser.IfBlockContext ctx) {

        ConditionBlock condition = new ConditionBlock(ctx);

        for (QLParser.BlockContext bctx: ctx.block()) {
            Block block = visitBlock(bctx);
            condition.addBlock(block);
        }

        return condition;
    }

    @Override
    public QuestionBlock visitQuestion(QLParser.QuestionContext ctx) {
        return new QuestionBlock(ctx);
    }
}
