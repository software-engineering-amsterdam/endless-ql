package org.uva.sea.ql;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import org.uva.sea.ql.parser.antlr.*;

public class QLCompiler extends QLBaseListener {

    /**
     * Compile a form specification
     * @param source Of the source location
     * @return
     */
    public Object compileScriptFile(CharStream source){

        //Get tokens
        QLLexer lexer = new QLLexer(source);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        //Parse the tree
        QLParser parser = new QLParser(tokens);
        //parser.addErrorListener(new ErrorListener());
        QLParser.FormContext form = parser.form();

        //Walk the tree and print out all elements
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, form);

        //Show the parse tree
        Trees.inspect(form, parser);

        return null;
    }


    @Override
    public void enterForm(QLParser.FormContext context) {
        System.out.printf("%nform %s {%n", context.result.getName());
    }


    @Override
    public void exitForm(QLParser.FormContext context) {
        System.out.printf("}%n");
    }


    @Override
    public void enterStatements(QLParser.StatementsContext context) {
        System.out.printf("statement %s", context.result.getStatementList());
    }


    @Override
    public void exitStatements(QLParser.StatementsContext context) {
        System.out.printf("%n");
    }
}



