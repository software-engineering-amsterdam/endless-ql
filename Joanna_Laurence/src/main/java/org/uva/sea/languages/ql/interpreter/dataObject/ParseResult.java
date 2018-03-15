package org.uva.sea.languages.ql.interpreter.dataObject;

import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;

public class ParseResult<T> {

    private final Messages warnings;
    private final T AST;

    public ParseResult(T AST, Messages warnings) {
        this.warnings = warnings;
        this.AST = AST;
    }

    public Messages getMessages() {
        return this.warnings;
    }

    public T getAST() {
        return this.AST;
    }
}
