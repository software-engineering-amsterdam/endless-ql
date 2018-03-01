package models.ast;

import models.ast.elements.Form;
import grammar.QLBaseVisitor;
import grammar.QLParser;

public class ASTBuilder extends QLBaseVisitor {

    private Form form = new Form();

    public Form getForm() {
        return form;
    }

    @Override
    public Object visitForm(QLParser.FormContext ctx) {
        this.form.setName(ctx.id.getText());
        return super.visitForm(ctx);
    }

    @Override
    public Object visitBlock(QLParser.BlockContext ctx) {
        //this.form.addBlock()

        if(ctx.ifBlock() != null) {
            System.out.println(">>COND>> "+ctx.ifBlock().getText());
            //ConditionBlock block = new ConditionBlock();

        }

        if (ctx.question() != null) {
            System.out.println("##QUES##" + ctx.question().getText());
        }
        return super.visitBlock(ctx);
    }
}
