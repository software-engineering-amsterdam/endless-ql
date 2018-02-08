package org.uva.sea.ql;

import java.io.IOException;


import org.antlr.v4.runtime.CharStream;

import org.antlr.v4.runtime.CommonTokenStream;

import org.antlr.v4.runtime.tree.ParseTreeWalker;


import ql.QLBaseListener;

import ql.QLLexer;

import ql.QLParser;

import ql.QLParser.ConditionalContext;

import ql.QLParser.FormContext;

import ql.QLParser.QuestionContext;


public class QLMain {


    public Object compileScriptFile(CharStream source) throws IOException {

        QLLexer lexer = new QLLexer(source);

        CommonTokenStream tokens = new CommonTokenStream(lexer);


        QLParser parser = new QLParser(tokens);

        parser.removeErrorListeners();

        parser.addErrorListener(new ErrorListener());


        FormContext form = parser.form();

        ParseTreeWalker walker = new ParseTreeWalker();

        walker.walk(this, form);

        return null;

    }


    @Override

    public void enterForm(FormContext ctx) {

        System.out.printf("%nform %s {%n", ctx.name().getText());

    }


    @Override

    public void exitForm(FormContext ctx) {

        System.out.printf("}%n");

    }


    @Override

    public void enterQuestion(QuestionContext ctx) {

        System.out.printf("\t%s : %s %s%n", ctx.name().getText(), ctx.TEXT().getText(), ctx.type().getText());

    }


    @Override

    public void enterConditional(ConditionalContext ctx) {

        System.out.printf("\tif (%s) {%n", ctx.condition().getText());

    }


    @Override

    public void exitConditional(ConditionalContext ctx) {

        System.out.printf("\t}%n");

    }
}



