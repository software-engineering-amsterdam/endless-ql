package org.uva.sea.languages.qls.interpreter;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.languages.astGenerator.BaseASTGenerator;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.IStaticAnalysis;
import org.uva.sea.languages.ql.parser.antlr.ErrorHandler;
import org.uva.sea.languages.qls.parser.antlr.QLSLexer;
import org.uva.sea.languages.qls.parser.antlr.QLSParser;
import org.uva.sea.languages.qls.parser.elements.Stylesheet;

public class ASTGenerator extends BaseASTGenerator<Stylesheet> {

    public ASTGenerator() {
        super(Arrays.asList(new IStaticAnalysis[]{

        }));
    }

    public Stylesheet createAST(CharStream source) {
        QLSLexer lexer = new QLSLexer(source);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLSParser parser = new QLSParser(tokens);

        ErrorHandler parseErrorListener = new ErrorHandler();
        parser.addErrorListener(parseErrorListener);

        QLSParser.StylesheetContext styleSheet = parser.stylesheet();

        return parseErrorListener.isError() ? null : styleSheet.result;
    }


}
