package com.chariotit.uva.sc.qdsl;

import com.chariotit.uva.sc.qdsl.parser.GLVisitor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.*;

import com.chariotit.uva.sc.qdsl.grammar.QLLexer;
import com.chariotit.uva.sc.qdsl.grammar.QLParser;

@Component
public class ApplicationRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        if (args.length == 0) {
            System.err.println("Missing filename argument. Please provide input file.");
            System.exit(1);
        }

        String filePath = args[0];


        CharStream input = CharStreams.fromFileName(filePath);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        ParseTree tree = parser.forms();
        GLVisitor visitor = new GLVisitor();
//
        System.out.println(visitor.visit(tree));

    }
}
