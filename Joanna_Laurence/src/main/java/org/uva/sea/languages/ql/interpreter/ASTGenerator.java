package org.uva.sea.languages.ql.interpreter;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.dataObject.ParseResult;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.antlr.ErrorHandler;
import org.uva.sea.languages.ql.parser.antlr.QLLexer;
import org.uva.sea.languages.ql.parser.antlr.QLParser;
import org.uva.sea.languages.ql.parser.elements.Form;

class ASTGenerator {
    final ParseResult<Form> createAST(final CharStream source) {
        final QLLexer lexer = new QLLexer(source);
        final CommonTokenStream tokens = new CommonTokenStream(lexer);

        final QLParser parser = new QLParser(tokens);

        final ErrorHandler parseErrorListener = new ErrorHandler();
        parser.addErrorListener(parseErrorListener);

        final QLParser.FormContext form = parser.form();

        final Messages parseMessages = parseErrorListener.getMessages();
        if (parseMessages.hasMessagePresent(MessageTypes.ERROR))
            return new ParseResult<>(null, parseMessages);

        return new ParseResult<>(form.result, parseMessages);
    }
}



