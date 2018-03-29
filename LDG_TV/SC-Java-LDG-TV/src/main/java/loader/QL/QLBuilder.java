package loader.QL;

import antlr.ql.FormLexer;
import antlr.ql.FormParser;
import antlr.qls.StylesheetLexer;
import antlr.qls.StylesheetParser;
import domain.model.ast.FormNode;
import domain.model.stylesheet.Stylesheet;
import loader.QLS.QLSLoader;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class QLBuilder {

    private final LoaderErrorListener loaderErrorListener;
    private final BaseErrorListener errorListener;

    public QLBuilder(BaseErrorListener errorListener, LoaderErrorListener loaderErrorListener) {
        this.errorListener = errorListener;
        this.loaderErrorListener = loaderErrorListener;
    }

    public FormNode toFormNode(String qlSource) {
        // Parse input field and create AST
        CharStream stream = CharStreams.fromString(qlSource);
        FormLexer lexer = new FormLexer(stream);

        FormParser parser = new FormParser(new CommonTokenStream(lexer));

        parser.addErrorListener(errorListener);

        FormParser.FormBuilderContext tree = parser.formBuilder();
        QLLoader loader = new QLLoader();
        loader.addErrorListener(loaderErrorListener);

        ParseTreeWalker.DEFAULT.walk(loader, tree);

        return loader.getFormNode();
    }


}
