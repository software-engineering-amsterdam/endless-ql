package ql.analysis;

import ql.model.Form;
import ql.model.Question;

import java.util.ArrayList;
import java.util.List;

public class UnknownIdentifiersDetector {

    private final Form form;

    public UnknownIdentifiersDetector(Form form) {
        this.form = form;
    }

    public void detectUnknownIdentifiers() {
        ReferencedIdentifiersVisitor referencedIdentifiersVisitor = new ReferencedIdentifiersVisitor();

        List<String> formQuestionIdentifiers = new ArrayList<>();
        List<String> referencedIdentifiers = new ArrayList<>();
        for (Question question : form.questions) {
            formQuestionIdentifiers.add(question.name);

            // Add all references to variables in both the question's computed answer and the question's condition
            if (question.isComputed()) {
                referencedIdentifiers.addAll(referencedIdentifiersVisitor.visit(question.computedAnswer));
            }
            referencedIdentifiers.addAll(referencedIdentifiersVisitor.visit(question.condition));
        }

        // Determine which identifiers are referenced but no question exists with such identifier
        // Subtraction of formQuestionIdentifiers - referencedIdentifiers
        referencedIdentifiers.removeAll(formQuestionIdentifiers);

        if (!referencedIdentifiers.isEmpty()) {
            throw new IllegalArgumentException("Unknown reference(s) to identifiers: " + referencedIdentifiers);
        }
    }
}
