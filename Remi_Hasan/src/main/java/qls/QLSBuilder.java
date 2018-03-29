package qls;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.antlr.ParseErrorListener;
import ql.model.Form;
import qls.analysis.QLSErrorAnalyzer;
import qls.antlr.QLSLexer;
import qls.antlr.QLSParser;
import qls.builder.StyleSheetBuilder;
import qls.model.StyleSheet;

import java.io.IOException;
import java.io.InputStream;

public class QLSBuilder {

    private Form qlForm;

    public QLSBuilder(Form qlForm) {
        this.qlForm = qlForm;
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
        StyleSheetBuilder visitor = new StyleSheetBuilder();
        StyleSheet styleSheet = visitor.visit(parser.root());

        // Analysis
        QLSErrorAnalyzer qlsErrorAnalyzer = new QLSErrorAnalyzer();
        qlsErrorAnalyzer.analyze(this.qlForm, styleSheet);

        return styleSheet;
    }
}