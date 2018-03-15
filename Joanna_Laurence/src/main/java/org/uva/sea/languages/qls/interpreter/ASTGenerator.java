package org.uva.sea.languages.qls.interpreter;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.dataObject.ParseResult;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.antlr.ErrorHandler;
import org.uva.sea.languages.qls.parser.antlr.QLSLexer;
import org.uva.sea.languages.qls.parser.antlr.QLSParser;
import org.uva.sea.languages.qls.parser.elements.Stylesheet;

public class ASTGenerator {


    public ParseResult<Stylesheet> createAST(CharStream source) {
        QLSLexer lexer = new QLSLexer(source);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLSParser parser = new QLSParser(tokens);

        ErrorHandler parseErrorListener = new ErrorHandler();
        parser.addErrorListener(parseErrorListener);

        QLSParser.StylesheetContext styleSheet = parser.stylesheet();

        Messages parseMessages = parseErrorListener.getMessages();
        if (parseMessages.hasMessagePresent(MessageTypes.ERROR))
            return new ParseResult<>(null, parseMessages);

        return new ParseResult<>(styleSheet.result, parseMessages);
    }
}
