package com.chariotit.uva.sc.qdsl;

import com.chariotit.uva.sc.qdsl.ast.node.Form;
import com.chariotit.uva.sc.qdsl.grammar.QLBaseVisitor;
import com.chariotit.uva.sc.qdsl.grammar.QLParser;
import org.antlr.v4.runtime.Parser;


public class FormsVisitor<T> extends QLBaseVisitor<T> {

    @Override
    public T visitForm(QLParser.FormContext ctx) {

        System.out.println("Visiting form");

        return new Form(visitChildren(ctx));
//        return (T)"test";
//        return visitChildren(ctx);
    }

    @Override
    public T visitQuestion(QLParser.QuestionContext ctx) {

        System.out.println("Visiting question: " + ctx.getText());

        return (T)ctx.getText();

//        return visitChildren(ctx);

    }

    @Override
    public T visitLabel(QLParser.LabelContext ctx) {
        return (T)ctx.getText();
    }


    @Override
    public T visitForms(QLParser.FormsContext ctx) {

        System.out.println("Visiting forms");

        return (T)[visitChilldren(ctx)];
    }

    @Override
    public T visitElem(QLParser.ElemContext ctx) {

        System.out.println("visiting element: " + ctx.getText());

        return super.visitElem(ctx);
    }

    @Override
    public T visitLine_elem(QLParser.Line_elemContext ctx) {

//        T[] output = visitChildren(ctx);

        System.out.println(visitChildren(ctx));

        System.out.println("visiting line element " + ctx.getText());

        return super.visitLine_elem(ctx);
    }

    @Override
    public T visitBlock_elem(QLParser.Block_elemContext ctx) {
        return super.visitBlock_elem(ctx);
    }

    @Override
    public T visitIf_block(QLParser.If_blockContext ctx) {
        return super.visitIf_block(ctx);
    }

    @Override
    public T visitStmt(QLParser.StmtContext ctx) {
        return super.visitStmt(ctx);
    }

    @Override
    public T visitExpr(QLParser.ExprContext ctx) {
        return super.visitExpr(ctx);
    }

    @Override
    public T visitConstant(QLParser.ConstantContext ctx) {
        return super.visitConstant(ctx);
    }

    @Override
    public T visitBinop(QLParser.BinopContext ctx) {
        return super.visitBinop(ctx);
    }

    @Override
    public T visitUnop(QLParser.UnopContext ctx) {
        return super.visitUnop(ctx);
    }

    @Override
    public T visitType_expr(QLParser.Type_exprContext ctx) {

        System.out.println("Type expression " + ctx.getText());

        return (T)ctx.getText();
    }

    @Override
    public T visitType(QLParser.TypeContext ctx) {
        return super.visitType(ctx);
    }


}