package com.chariotit.uva.sc.qdsl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.*;

import com.chariotit.uva.sc.qdsl.grammar.QLLexer;
import com.chariotit.uva.sc.qdsl.grammar.QLParser;

@Component
public class GLRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        if (args.length == 0) {
            System.err.println("Missing filename argument. Please provide input file.");
            System.exit(1);
        }

        String filePath = args[0];

        System.out.println(filePath);

        CharStream input = CharStreams.fromFileName(filePath);
        

//        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(filePath));

        // file
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        ParseTree tree = parser.forms();
//        ParseTree tree = parser.form();
//        //ParseTree tree = pxarser.question();
//        System.out.println(tree.toStringTree(parser));
//
//        System.out.println(parser.getGrammarFileName());
//        System.out.println(parser.getTokenNames());
//        System.out.println(tree.getChildCount());
// //       System.out.println(tree.getChild(7).getText());
////
        FormsVisitor visitor = new FormsVisitor();

        System.out.println("---------- Result: ----------");
        System.out.println("visitor result: " + visitor.visit(tree));
    }
}
