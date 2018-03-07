package org.uva.sea.dataObject;

import org.uva.sea.ql.elements.Form;
import org.uva.sea.staticAnalysis.helpers.Messages;

public class ASTResult {

    private Messages warnings;
    private Form AST;

    public ASTResult(Form AST, Messages warnings) {
        this.warnings = warnings;
        this.AST = AST;
    }

    public Messages getWarnings() {
        return warnings;
    }

    public Form getAST() {
        return AST;
    }
}
