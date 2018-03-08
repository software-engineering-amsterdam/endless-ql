package com.chariotit.uva.sc.qdsl;

import com.chariotit.uva.sc.qdsl.parser.QLVisitor;
import com.chariotit.uva.sc.qdsl.QLFrame;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.*;

import com.chariotit.uva.sc.qdsl.grammar.QLLexer;
import com.chariotit.uva.sc.qdsl.grammar.QLParser;

import java.awt.EventQueue;
import javax.swing.JFrame;

@Component
public class ApplicationRunner implements CommandLineRunner {
//
//    /**
//     * Pull in the JFrame to be displayed.
//     */
//    @Autowired
//    private QLFrame frame;

    @Override
    public void run(String... args) throws Exception {

        if (args.length == 0) {
            System.err.println("Missing filename argument. Please provide input file.");
            System.exit(1);
        }

        System.out.println("Starting application");

        String filePath = args[0];

        CharStream input = CharStreams.fromFileName(filePath);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        ParseTree tree = parser.forms();
        QLVisitor visitor = new QLVisitor();

        System.out.println(visitor.visit(tree));

//        QLFrame frame = new QLFrame();

        QLFormBuilder builder = new QLFormBuilder();

        builder.showForm();

//        QLFormFrame frame = new QLFormFrame();

        System.out.println("Frame generated");
    }

}