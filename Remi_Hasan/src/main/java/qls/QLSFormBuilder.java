package qls;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ql.analysis.SymbolTable;
import ql.model.Form;
import ql.parser.ParseErrorListener;
import qls.analysis.QuestionAnalyzer;
import qls.analysis.TypeChecker;
import qls.model.StyleSheet;
import qls.parser.QLSLexer;
import qls.parser.QLSParser;
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

        // TODO: remove
        //parser.reset();
        //Trees.inspect(parser.root(), parser);

        // Debug: print object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(styleSheet));

        // Analysis
        QuestionAnalyzer questionAnalyzer = new QuestionAnalyzer();
        questionAnalyzer.detectDuplicateQuestions(styleSheet);
        questionAnalyzer.detectUnknownQuestions(this.qlForm, styleSheet);
        questionAnalyzer.detectUnplacedQuestions(this.qlForm, styleSheet);

        TypeChecker typeChecker = new TypeChecker();
        typeChecker.typeCheck(this.qlForm, styleSheet);

        return styleSheet;
    }
}