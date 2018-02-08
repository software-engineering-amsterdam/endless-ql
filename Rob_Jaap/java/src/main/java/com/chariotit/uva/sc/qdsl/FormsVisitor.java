package com.chariotit.uva.sc.qdsl;



public class FormsVisitor<T> extends GrammarBaseVisitor <T>{


    @Override public T visitForm(GrammarParser.FormContext ctx) {

        System.out.println("Visiting form test");

        return visitChildren(ctx);

    }

}