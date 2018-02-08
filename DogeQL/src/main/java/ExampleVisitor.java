package main.java;

import grammar.SimpleGrammarExampleBaseVisitor;
import grammar.SimpleGrammarExampleParser;

public class ExampleVisitor extends SimpleGrammarExampleBaseVisitor<String> {


    @Override
    public String visitExpr(SimpleGrammarExampleParser.ExprContext ctx) {
        visitChildren(ctx);

        if (ctx.getChildCount() == 1) {
            System.out.println(ctx.getChild(0));
        }else {
            System.out.println(ctx.getChild(1));
        }

        return null;
    }

    @Override
    public String visitProg(SimpleGrammarExampleParser.ProgContext ctx) {
        visitChildren(ctx);

        return null;
    }

}
