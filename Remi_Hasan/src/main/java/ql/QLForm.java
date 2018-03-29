package ql;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.analysis.error.QLErrorAnalyzer;
import ql.analysis.warning.QLWarningAnalyzer;
import ql.antlr.ParseErrorListener;
import ql.antlr.QLLexer;
import ql.antlr.QLParser;
import ql.builder.FormBuilder;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.SymbolTable;
import ql.evaluation.value.Value;
import ql.model.Form;
import ql.model.expression.Expression;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class QLForm {

    private final Form form;
    private final SymbolTable symbolTable;
    private final ExpressionEvaluator expressionEvaluator;

    public QLForm(InputStream inputStream) throws IllegalArgumentException, UnsupportedOperationException, IOException {
        this.form = this.buildForm(inputStream);

        this.symbolTable = new SymbolTable();
        this.symbolTable.buildTable(this.form);
        this.expressionEvaluator = new ExpressionEvaluator(this.symbolTable);

        QLErrorAnalyzer qlErrorAnalyzer = new QLErrorAnalyzer();
        qlErrorAnalyzer.analyze(form, symbolTable);
    }

    private Form buildForm(InputStream stream) throws IOException {
        QLLexer lexer = new QLLexer(CharStreams.fromStream(stream));
        lexer.removeErrorListeners();
        lexer.addErrorListener(ParseErrorListener.INSTANCE);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLParser parser = new QLParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(ParseErrorListener.INSTANCE);

        // Visit parse tree and build form model
        FormBuilder visitor = new FormBuilder();
        return visitor.visit(parser.root());
    }

    public void updateSymbolTable(String identifier, Expression expression) {
        this.symbolTable.setExpression(identifier, expression);
    }

    public Value getAnswer(String identifier) {
        Expression expression = this.symbolTable.getExpression(identifier);
        return this.expressionEvaluator.visit(expression);
    }

    public Value evaluateExpression(Expression expression) {
        return this.expressionEvaluator.visit(expression);
    }

    public Set<String> getWarnings() {
        QLWarningAnalyzer qlWarningAnalyzer = new QLWarningAnalyzer();
        return qlWarningAnalyzer.analyze(this.form, this.symbolTable);
    }

    public Form getForm() {
        return form;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public String getIdentifier() {
        return form.getIdentifier();
    }

    public void exportResults(File exportFile) throws IOException {
        QLExporter.export(this, exportFile);
    }
}
