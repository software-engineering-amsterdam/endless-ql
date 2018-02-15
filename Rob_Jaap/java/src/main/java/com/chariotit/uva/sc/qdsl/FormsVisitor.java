package com.chariotit.uva.sc.qdsl;

import com.chariotit.uva.sc.qdsl.ast.node.Form;
import com.chariotit.uva.sc.qdsl.grammar.QLBaseVisitor;
import com.chariotit.uva.sc.qdsl.grammar.QLParser;
import org.antlr.v4.runtime.Parser;


public class FormsVisitor<T> extends QLBaseVisitor <T> {

    @Override
    public T visitForm(QLParser.FormContext ctx) {

        System.out.println("Visiting form");

        return new Form(visitChildren(ctx));

        return visitChildren(ctx);
    }


    @Override public T visitQuestion(GrammarParser.QuestionContext ctx) {

        System.out.println("Visiting question");

        return visitChildren(ctx);

    }

    @Override public T visitLabel(GrammarParser.LabelContext ctx){

        System.out.println("Visiting label");

        System.out.println(ctx);

        return visitChildren(ctx);

    }


}