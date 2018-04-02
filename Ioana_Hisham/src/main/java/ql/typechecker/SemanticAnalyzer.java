package ql.typechecker;

import ql.ast.Form;
import ql.typechecker.messages.Messages;

public class SemanticAnalyzer {
    private final Messages messages;
    private final TypeChecker typechecker;

    public SemanticAnalyzer() {
        messages = new Messages();
        typechecker = new TypeChecker(messages);
    }

    public Messages checkForm(Form form) {
        typechecker.visit(form);

        return messages;
    }
}
