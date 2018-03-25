package com.chariotit.uva.sc.qdsl;

import com.chariotit.uva.sc.qdsl.ast.ql.TypeChecker;
import com.chariotit.uva.sc.qdsl.ast.ql.node.QLAstRoot;
import com.chariotit.uva.sc.qdsl.ast.common.TypeCheckError;
import com.chariotit.uva.sc.qdsl.ast.qls.Validator;
import com.chariotit.uva.sc.qdsl.ast.qls.node.Stylesheet;
import com.chariotit.uva.sc.qdsl.formbuilder.FormBuilder;
import com.chariotit.uva.sc.qdsl.grammar.QLSLexer;
import com.chariotit.uva.sc.qdsl.grammar.QLSParser;
import com.chariotit.uva.sc.qdsl.parser.QLSVisitor;
import com.chariotit.uva.sc.qdsl.parser.QLVisitor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.*;

import com.chariotit.uva.sc.qdsl.grammar.QLLexer;
import com.chariotit.uva.sc.qdsl.grammar.QLParser;

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


    private void runProgram(ApplicationParameters parameters) throws IOException {
        QLAstRoot astRoot = getQLFromFilename(parameters.getQlFilename());


        // Run Typechecker
        TypeChecker typeChecker = new TypeChecker();
        List<TypeCheckError> errors = typeChecker.typeCheckAst(astRoot);

        TypeCheckError.print(errors);

        if (TypeCheckError.listContainsLevel(errors, TypeCheckError.Level.ERROR)) {
            System.exit(1);
        }

        FormBuilder builder = new FormBuilder();
        builder.buildForm(astRoot);


        if (parameters.getQlsFilename() != null) {
            Stylesheet stylesheet = getQLSFromFilename(parameters.getQlsFilename());

            Validator validator = new Validator(astRoot);
            List<TypeCheckError> qlsErrors = validator.typeCheckQLS(stylesheet);

            TypeCheckError.print(qlsErrors);

            if (TypeCheckError.listContainsLevel(qlsErrors, TypeCheckError.Level.ERROR)) {
                System.exit(1);
            }
        }

//        QLFormBuilder builder = new QLFormBuilder();
//
//        builder.showForm();

    }

    @Override
    public void run(String... args) throws IOException {
        try {
            ApplicationParameters parameters = ApplicationParameters.get(args);
            runProgram(parameters);
        } catch (InvalidParametersException e) {
            ApplicationParameters.printHelp();
            System.exit(1);
        }
    }

}