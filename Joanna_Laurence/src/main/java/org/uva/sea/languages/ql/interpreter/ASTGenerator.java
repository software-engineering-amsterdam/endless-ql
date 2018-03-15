package org.uva.sea.languages.ql.interpreter;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.languages.ql.antlr.QLLexer;
import org.uva.sea.languages.ql.antlr.QLParser;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.dataObject.ParseResult;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.antlr.ErrorHandler;
import org.uva.sea.languages.ql.parser.elements.Form;

public class ASTGenerator {
    /**
     * Create AST for specification
     *
     * @param source
     * @return
     */
    protected ParseResult<Form> createAST(CharStream source) {
        QLLexer lexer = new QLLexer(source);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        QLParser parser = new QLParser(tokens);

        ErrorHandler parseErrorListener = new ErrorHandler();
        parser.addErrorListener(parseErrorListener);

        QLParser.FormContext form = parser.form();

        Messages parseMessages = parseErrorListener.getMessages();
        if (parseMessages.hasMessagePresent(MessageTypes.ERROR))
            return new ParseResult<>(null, parseMessages);

        return new ParseResult<>(form.result, parseMessages);
    }
}



