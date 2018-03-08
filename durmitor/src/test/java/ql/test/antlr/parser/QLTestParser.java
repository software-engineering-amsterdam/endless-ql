package ql.test.antlr.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import ql.grammar.QLLexer;
import ql.grammar.QLParser;
import ql.visitors.QLVisitorToAst;

public class QLTestParser {

    QLVisitorToAst visitor = new QLVisitorToAst();
    
    public Object parseForm(String expr) {
        
        QLParser parser = new QLParser(new CommonTokenStream(new QLLexer(CharStreams.fromString(expr))));
        
        return parser.form().accept(visitor);
    }
    
    public Object parseStmt(String expr) {
        
        QLParser parser = new QLParser(new CommonTokenStream(new QLLexer(CharStreams.fromString(expr))));
        
        return parser.statement().accept(visitor);
    }
    
    public Object parseIdentifier(String expr) {
        
        QLParser parser = new QLParser(new CommonTokenStream(new QLLexer(CharStreams.fromString(expr))));
        
        return parser.identifier().accept(visitor);
    }
    
    public Object parseExpr(String expr) {
        
        QLParser parser = new QLParser(new CommonTokenStream(new QLLexer(CharStreams.fromString(expr))));
        
        return parser.expr().accept(visitor);
    }
    
    public Object parseType(String expr) {
        
        QLParser parser = new QLParser(new CommonTokenStream(new QLLexer(CharStreams.fromString(expr))));
        
        return parser.type().accept(visitor);
    }
}
