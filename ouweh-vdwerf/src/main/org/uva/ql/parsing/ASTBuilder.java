package org.uva.ql.parsing;

import antlr.generated.QLLexer;
import antlr.generated.QLParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.ql.ast.Form;
import org.uva.ql.ast.expression.Expression;

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

    public QLParser getQLParser(String input){
        CharStream charStream = CharStreams.fromString(input);
        QLLexer lexer = new QLLexer(charStream);
        return  new QLParser(new CommonTokenStream(lexer));
    }

    public Expression getExpression(QLParser parser) {
        ParseTreeVisitor visitor = new ParseTreeVisitor();
        QLParser.ExpressionContext context = parser.expression();
        return (Expression) visitor.visit(context);
    }
}
