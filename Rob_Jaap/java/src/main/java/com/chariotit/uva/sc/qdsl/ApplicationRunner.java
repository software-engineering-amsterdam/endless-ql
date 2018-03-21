package com.chariotit.uva.sc.qdsl;

import com.chariotit.uva.sc.qdsl.ast.ql.TypeChecker;
import com.chariotit.uva.sc.qdsl.ast.ql.node.QLAstRoot;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.TypeCheckError;
import com.chariotit.uva.sc.qdsl.ast.qls.Validator;
import com.chariotit.uva.sc.qdsl.ast.qls.node.Stylesheet;
import com.chariotit.uva.sc.qdsl.formbuilder.FormBuilder;
import com.chariotit.uva.sc.qdsl.grammar.QLSLexer;
import com.chariotit.uva.sc.qdsl.grammar.QLSParser;
import com.chariotit.uva.sc.qdsl.parser.QLSVisitor;
import com.chariotit.uva.sc.qdsl.parser.QLVisitor;
import com.chariotit.uva.sc.qdsl.QLFrame;

import sun.tools.jar.CommandLine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import org.apache.commons.cli.*;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.*;

import com.chariotit.uva.sc.qdsl.grammar.QLLexer;
import com.chariotit.uva.sc.qdsl.grammar.QLParser;
import sun.tools.jar.CommandLine;

import java.io.IOException;
import java.util.List;

@Component
public class ApplicationRunner implements CommandLineRunner {
//
//    /**
//     * Pull in the JFrame to be displayed.
//     */
//    @Autowired
//    private QLFrame frame;

    private QLAstRoot getQLFromFilename(String filename) throws IOException {

        CharStream input = CharStreams.fromFileName(filename);
        QLLexer lexer = new QLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        ParseTree tree = parser.forms();
        QLVisitor visitor = new QLVisitor();

        return (QLAstRoot) visitor.visit(tree);
    }

    private Stylesheet getQLSFromFilename(String filename) throws IOException {
        CharStream input = CharStreams.fromFileName(filename);
        QLSLexer lexer = new QLSLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLSParser parser = new QLSParser(tokens);
        ParseTree tree = parser.stylesheet();
        QLSVisitor visitor = new QLSVisitor();

        return (Stylesheet) visitor.visit(tree);
    }

    @Override
    public void run(String... args) throws IOException {
//        String filename = "";
//        String qlsFilename = "";
//        String qlsArgumentShort = "s";
//        String qlsArgumentLong = "style";
//        CommandLine commandLine;
//        CommandLineParser parser = new DefaultParser();
//
//        Options options = new Options();
//
//        // AST is initialised here.
//
//        Option qlsOption = Option.builder(qlsArgumentShort)
//                .required(false)
//                .longOpt(qlsArgumentLong)
//                .desc("Location of the QLS Stylesheet")
//                .hasArg()
//                .build();
//
//        options.addOption(qlsOption);
//
//        try {
//            commandLine = parser.parse(options, args);
//
//            if (commandLine.hasOption(qlsArgumentLong)) {
//                qlsFilename = commandLine.getOptionValue(qlsArgumentLong);
//            }
//
//            String[] remainder = commandLine.getArgs();
//
//            if (remainder.length != 1) {
//                throw new Exception("");
//            }
//
//            filename = remainder[0];
//
//        } catch (Exception e) {
//            HelpFormatter formatter = new HelpFormatter();
//            formatter.printHelp("QLProgram", options);
//            System.exit(1);
//        }

        if (args.length == 0) {
            System.err.println("Missing filename argument. Please provide input file.");
            System.exit(1);
        }

        System.out.println("Starting application");

        String filename = args[0];

        QLAstRoot astRoot = getQLFromFilename(filename);

        FormBuilder builder = new FormBuilder();
        builder.buildForm(astRoot);


        QLFormBuilder builder1 = new QLFormBuilder(astRoot);


//
//
//        // Run Typechecker
//        TypeChecker typeChecker = new TypeChecker();
//        List<TypeCheckError> errors = typeChecker.typeCheckAst(astRoot);
//        Boolean abort = false;
//
//        for (TypeCheckError error : errors) {
//            System.out.println(String.format(
//                    "%4s line %d, column %d: %s",
//                    error.getLevel(),
//                    error.getLineNumber(),
//                    error.getColumnNumber(),
//                    error.getMessage()
//            ));
//
//            if (error.getLevel() == TypeCheckError.Level.ERROR) {
//                abort = true;
//            }
//        }
//
//        if (abort) {
//            System.exit(1);
//        }
//
//
//        if (!qlsFilename.equals("")) {
//            Stylesheet stylesheet = getQLSFromFilename(qlsFilename);
//
//            Validator validator = new Validator(astRoot);
//            List<TypeCheckError> qlsErrors = validator.typeCheckQLS(stylesheet);
//
//            for (TypeCheckError error : qlsErrors) {
//                System.out.println(String.format(
//                        "%4s line %d, column %d: %s",
//                        error.getLevel(),
//                        error.getLineNumber(),
//                        error.getColumnNumber(),
//                        error.getMessage()
//                ));
//
//                if (error.getLevel() == TypeCheckError.Level.ERROR) {
//                    abort = true;
//                }
//            }
//
//            if (abort) {
//                System.exit(1);
//            }
//
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