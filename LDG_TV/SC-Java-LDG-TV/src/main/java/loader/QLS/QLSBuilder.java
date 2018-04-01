package loader.QLS;

import antlr.qls.StylesheetLexer;
import antlr.qls.StylesheetParser;
import domain.model.ast.FormNode;
import domain.model.stylesheet.Stylesheet;
import loader.QL.LoaderErrorListener;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class QLSBuilder {
    private final LoaderErrorListener loaderErrorListener;
    private final BaseErrorListener errorListener;

    public QLSBuilder(BaseErrorListener errorListener, LoaderErrorListener loaderErrorListener) {
        this.errorListener = errorListener;
        this.loaderErrorListener = loaderErrorListener;
    }

    public Stylesheet toStylesheet(String qlsSource, FormNode formNode) {
        CharStream qlsStream = CharStreams.fromString(qlsSource);
        StylesheetLexer qlsLexer = new StylesheetLexer(qlsStream);

        StylesheetParser qlsParser = new StylesheetParser(new CommonTokenStream(qlsLexer));

        qlsParser.addErrorListener(errorListener);

        StylesheetParser.StylesheetBuilderContext stylesheetTree = qlsParser.stylesheetBuilder();
        QLSLoader qlsLoader = new QLSLoader(formNode);
        ParseTreeWalker.DEFAULT.walk(qlsLoader, stylesheetTree);

        return qlsLoader.getStyleSheet();
    }
}
