package qls;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.evaluation.SymbolTable;
import ql.model.Form;
import ql.antlr.ParseErrorListener;
import qls.analysis.QLSErrorAnalyzer;
import qls.analysis.QuestionAnalyzer;
import qls.analysis.TypeChecker;
import qls.model.StyleSheet;
import qls.antlr.QLSLexer;
import qls.antlr.QLSParser;
import qls.visitor.VisitorStyleSheet;

import java.io.IOException;
import java.io.InputStream;

public class QLSFormBuilder {

    private Form qlForm;
    private SymbolTable symbolTable;

    public QLSFormBuilder(Form qlForm, SymbolTable symbolTable) {
        this.qlForm = qlForm;
        this.symbolTable = symbolTable;
    }

    public StyleSheet parseStyleSheet(InputStream stream) throws IllegalArgumentException, UnsupportedOperationException, IOException {
        QLSLexer lexer = new QLSLexer(CharStreams.fromStream(stream));
        lexer.removeErrorListeners();
        lexer.addErrorListener(ParseErrorListener.INSTANCE);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLSParser parser = new QLSParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(ParseErrorListener.INSTANCE);

        // Visit parse tree and build model
        VisitorStyleSheet visitor = new VisitorStyleSheet();
        StyleSheet styleSheet = visitor.visit(parser.root());

        // TODO: remove debugging tree
        //parser.reset();
        //Trees.inspect(parser.root(), parser);

        // Debug: print object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(styleSheet));

        // Analysis
        QLSErrorAnalyzer qlsErrorAnalyzer = new QLSErrorAnalyzer();
        qlsErrorAnalyzer.analyze(this.qlForm, styleSheet);

        return styleSheet;
    }
}