package ql.analysis;

import ql.QLBaseVisitor;
import ql.model.Form;
import ql.model.Question;
import ql.model.expression.ExpressionIdentifier;

import java.util.ArrayList;
import java.util.List;

public class UnknownIdentifiersDetector extends QLBaseVisitor {

    private final Form form;

    public UnknownIdentifiersDetector(Form form) {
        this.form = form;
    }

    public void detectUnknownIdentifiers() {
        List<String> formQuestionIdentifiers = IdentifiersCollector.collectQuestionIdentifiers(this.form);
        List<String> referencedIdentifiers = IdentifiersCollector.collectReferencedIdentifiers(this.form);

        // Determine which identifiers are referenced but no question exists with such identifier
        // Subtraction of formQuestionIdentifiers - referencedIdentifiers
        referencedIdentifiers.removeAll(formQuestionIdentifiers);

        if (!referencedIdentifiers.isEmpty()) {
            throw new IllegalArgumentException("Unknown reference(s) to identifiers: " + referencedIdentifiers);
        }
    }
}
