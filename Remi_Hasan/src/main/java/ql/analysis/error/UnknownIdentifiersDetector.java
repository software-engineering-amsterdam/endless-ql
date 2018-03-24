package ql.analysis.error;

import ql.analysis.IdentifiersCollector;
import ql.evaluation.SymbolTable;
import ql.model.Form;

import java.util.List;
import java.util.Set;

public class UnknownIdentifiersDetector implements IQLErrorAnalysis {

    @Override
    public void analyze(Form form, SymbolTable symbolTable) {
        // All question identifiers in the form
        Set<String> formQuestionIdentifiers = IdentifiersCollector.collectQuestionIdentifiers(form);

        // All references to identifiers in expressions in the form
        List<String> referencedIdentifiers = IdentifiersCollector.collectReferencedIdentifiers(form);

        // Determine which identifiers are referenced but no question exists with such identifier
        // Subtraction of formQuestionIdentifiers - referencedIdentifiers
        referencedIdentifiers.removeAll(formQuestionIdentifiers);

        if (!referencedIdentifiers.isEmpty()) {
            throw new IllegalArgumentException("Unknown reference(s) to identifiers: " + referencedIdentifiers);
        }
    }
}
