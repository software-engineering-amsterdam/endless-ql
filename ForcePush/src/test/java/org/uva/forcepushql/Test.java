package org.uva.forcepushql;

import antlr.GrammarLexer;
import antlr.GrammarParser;
import org.antlr.v4.runtime.*;

import java.io.File;

public class Test {
    public static void main(String[] args) throws Exception {

        File testFile = new File("antlr/TestInput.txt");
 // create a CharStream that reads from standard input
        ANTLRInputStream input = new ANTLRInputStream(String.valueOf(testFile));
// create a lexer that feeds off of input CharStream
        GrammarLexer lexer = new GrammarLexer(input);
// create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
// create a parser that feeds off the tokens buffer
        GrammarParser parser = new GrammarParser(tokens);
// begin parsing at rule r
        parser.question();
    }
}