package com.chariotit.uva.sc.qdsl;

public class FormsVisitor<T> extends GrammarBaseVisitor <T>{


    @Override public T visitForm(GrammarParser.FormContext ctx) {

        System.out.println("Visiting form");

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