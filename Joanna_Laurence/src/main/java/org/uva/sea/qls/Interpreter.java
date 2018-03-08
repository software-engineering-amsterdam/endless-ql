package org.uva.sea.qls;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.ql.interpreter.exceptions.StaticAnalysisError;
import org.uva.sea.ql.parser.antlr.ErrorHandler;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.qls.parser.antlr.QLSLexer;
import org.uva.sea.qls.parser.antlr.QLSParser;
import org.uva.sea.qls.parser.elements.Stylesheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.antlr.v4.gui.Trees;

public class Interpreter {

    public Stylesheet generate(String styleSpecification) throws IOException, StaticAnalysisError {

        QLSLexer lexer = new QLSLexer(toCharStream(styleSpecification));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLSParser parser = new QLSParser(tokens);

        ErrorHandler parseErrorListener = new ErrorHandler();
        parser.addErrorListener(parseErrorListener);

        QLSParser.StylesheetContext styleSheet = parser.stylesheet();

        Trees.inspect(styleSheet, parser);

        return parseErrorListener.isError() ? null : styleSheet.result;
    }


    /**
     * Convert file name to resource
     *
     * @param fileName The location of the file
     * @return CharStream
     * @throws IOException
     */
    private CharStream toCharStream(String fileName) throws IOException {
        return CharStreams.fromStream(new FileInputStream(fileName));
    }

}
