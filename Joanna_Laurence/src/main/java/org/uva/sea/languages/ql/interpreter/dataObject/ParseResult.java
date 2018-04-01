package org.uva.sea.languages.ql.interpreter.dataObject;

import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;

public class ParseResult<T> {

    private final Messages warnings;
    private final T ast;

    public ParseResult(T ast, Messages messages) {
        this.warnings = messages;
        this.ast = ast;
    }

    public Messages getMessages() {
        return this.warnings;
    }

    public T getAst() {
        return this.ast;
    }
}
