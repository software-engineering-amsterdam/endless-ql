package com.chariotit.uva.sc.qdsl;

import com.chariotit.uva.sc.qdsl.grammar.QLBaseVisitor;
import com.chariotit.uva.sc.qdsl.grammar.QLParser;
import org.antlr.v4.runtime.Parser;


public class FormsVisitor<T> extends QLBaseVisitor <T> {

    @Override
    public T visitForm(QLParser.FormContext ctx) {

        System.out.println("Visiting form test");

        return visitChildren(ctx);

    }

}