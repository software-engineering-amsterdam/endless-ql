package org.uva.sea.languages.ql.interpreter.dataObject;

import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;

public class ASTResult<T> {

    private Messages warnings;
    private T AST;

    public ASTResult(T AST, Messages warnings) {
        this.warnings = warnings;
        this.AST = AST;
    }

    public Messages getWarnings() {
        return warnings;
    }

    public T getAST() {
        return AST;
    }
}
