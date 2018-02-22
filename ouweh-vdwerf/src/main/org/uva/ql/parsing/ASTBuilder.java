package org.uva.ql.parsing;

import antlr.generated.QLLexer;
import antlr.generated.QLParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.ql.ast.Form;

public class ASTBuilder {
    public ASTBuilder(){

    }

    public Form buildAST(String input){
        QLParser parser = getQLParser(input);
        return getForm(parser);
    }

    private Form getForm(QLParser parser){
        QLParser.FormContext formContext =  parser.form();
        ParseTreeVisitor visitor = new ParseTreeVisitor();
        return (Form) visitor.visit(formContext);
    }

    private QLParser getQLParser(String input){
        CharStream charStream = CharStreams.fromString(input);
        QLLexer lexer = new QLLexer(charStream);
        return  new QLParser(new CommonTokenStream(lexer));
    }
}
