package com.chariotit.uva.sc.qdsl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.*;

import java.io.FileInputStream;
import java.io.IOException;

@Component
public class GLRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Hi there");

        String filePath = args[0];

        System.out.println(filePath);

        CharStream input = CharStreams.fromFileName(filePath);

//        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(filePath));

        // file
        GrammarLexer lexer = new GrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokens);
        ParseTree tree = parser.form();

        System.out.println(parser.getGrammarFileName());
//
        FormsVisitor visitor = new FormsVisitor();
//
        System.out.println(visitor.visit(tree));

    }
}
