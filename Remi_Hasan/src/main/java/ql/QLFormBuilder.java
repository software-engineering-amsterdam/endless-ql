package ql;

import ql.analysis.error.QLErrorAnalyzer;
import ql.analysis.warning.DuplicateLabelDetector;
import ql.evaluation.SymbolTable;
import ql.model.Form;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.antlr.QLLexer;
import ql.antlr.QLParser;
import ql.antlr.ParseErrorListener;
import ql.visitor.VisitorForm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class QLFormBuilder {

    private final SymbolTable symbolTable;

    public QLFormBuilder() {
        this.symbolTable = new SymbolTable();
    }

    public Form buildForm(InputStream stream) throws IllegalArgumentException, UnsupportedOperationException, IOException {
        QLLexer lexer = new QLLexer(CharStreams.fromStream(stream));
        lexer.removeErrorListeners();
        lexer.addErrorListener(ParseErrorListener.INSTANCE);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLParser parser = new QLParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(ParseErrorListener.INSTANCE);

        // Visit parse tree and build form model
        VisitorForm visitor = new VisitorForm();
        Form form = visitor.visit(parser.root());

        this.symbolTable.buildTable(form);

        // Perform all static analysis
        QLErrorAnalyzer qlErrorAnalyzer = new QLErrorAnalyzer();
        qlErrorAnalyzer.analyze(form, symbolTable);

        return form;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }
}