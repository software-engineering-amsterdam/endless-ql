package ql;

import ql.analysis.CycleDetector;
import ql.analysis.SymbolTable;
import ql.analysis.TypeChecker;
import ql.analysis.UnknownIdentifiersDetector;
import ql.model.Form;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.parser.QLLexer;
import ql.parser.QLParser;
import ql.visitor.VisitorForm;

import java.io.IOException;
import java.io.InputStream;

public class QLFormBuilder {

    private final SymbolTable symbolTable;

    public QLFormBuilder() {
        this.symbolTable = new SymbolTable();
    }

    public Form buildForm(InputStream stream) throws IllegalArgumentException, UnsupportedOperationException, IOException {
        QLLexer lexer = new QLLexer(CharStreams.fromStream(stream));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);

        // Visit parse tree and build form model
        VisitorForm visitor = new VisitorForm();
        Form form = visitor.visit(parser.root());

        this.symbolTable.buildTable(form);
        this.performAnalysis(form, this.symbolTable);

        return form;
    }

    private void performAnalysis(Form form, SymbolTable symbolTable) {
        UnknownIdentifiersDetector unknownIdentifiersDetector = new UnknownIdentifiersDetector(form);
        unknownIdentifiersDetector.detectUnknownIdentifiers();

        CycleDetector cycleDetector = new CycleDetector(form);
        cycleDetector.detectCycles();

        TypeChecker typeChecker = new TypeChecker(form, symbolTable);
        typeChecker.detectDuplicateQuestionsWithDifferentTypes();
        typeChecker.typeCheck();
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }
}