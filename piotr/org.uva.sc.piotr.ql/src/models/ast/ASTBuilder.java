package models.ast;

import models.ast.elements.block.Condition;
import models.ast.elements.Form;
import models.ast.elements.block.Block;
import grammar.QLBaseVisitor;
import grammar.QLParser;
import models.ast.elements.block.Question;

public class ASTBuilder extends QLBaseVisitor {

    @Override
    public Form visitForm(QLParser.FormContext ctx) {

        Form form = new Form(ctx.id.getText());

        for (QLParser.BlockContext blockContext : ctx.block()) {
            Block block = visitBlock(blockContext);
            form.addBlock(block);
        }

        return form;
    }

    @Override
    public Block visitBlock(QLParser.BlockContext ctx) {

        if (ctx.ifBlock() != null) {
            return visitIfBlock(ctx.ifBlock());
        } else if (ctx.question() != null) {
            return visitQuestion(ctx.question());
        }

        return null;
    }

    @Override
    public Condition visitIfBlock(QLParser.IfBlockContext ctx) {

        Condition condition = new Condition(ctx);

        for (QLParser.BlockContext blockContext : ctx.block()) {
            Block block = visitBlock(blockContext);
            condition.addBlock(block);
        }

//        if (ctx.elseBlock() != null) {
//
//        }

        return condition;
    }




    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        return new Question(ctx);
    }
}
