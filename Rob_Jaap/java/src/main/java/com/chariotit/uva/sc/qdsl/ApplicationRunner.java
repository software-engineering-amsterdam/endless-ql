package com.chariotit.uva.sc.qdsl;

import com.chariotit.uva.sc.qdsl.ast.TypeChecker;
import com.chariotit.uva.sc.qdsl.ast.node.AstRoot;
import com.chariotit.uva.sc.qdsl.ast.visitor.TypeCheckError;
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
import java.util.List;

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

//        QLFormBuilder builder = new QLFormBuilder();
//
//        builder.addQuestion("Test question");
//
//        builder.showForm();

        // AST is initialised here.
        AstRoot astRoot = (AstRoot)visitor.visit(tree);

//        // Run Typechecker
//        TypeChecker typeChecker = new TypeChecker();
//        List<TypeCheckError> errors = typeChecker.typeCheckAst(astRoot);
//
//        if (errors.size() > 0) {
//            for (TypeCheckError error : errors) {
//                System.out.println(String.format(
//                        "TypeCheckError line %d, column %d: %s",
//                        error.getLineNumber(),
//                        error.getColumnNumber(),
//                        error.getMessage()
//                ));
//            }
//
//            System.exit(1);
//        }

        // If everything ok, build form with new Visitor (extend NodeVisitor in
        // com.chariotit.uva.sc.qdsl.ast.visitor)
        // Keep variable values in in SymbolTable (in AstRoot)
        // SymbolTable is initialised in TypeChecker

//        QLFormBuilder builder = new QLFormBuilder();
//
//        builder.showForm();

        System.out.println("finished");
        System.out.println(astRoot);
    }

}