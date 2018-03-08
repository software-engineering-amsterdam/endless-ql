package validators;

import ast.model.expressions.values.VariableReference;
import ast.model.statements.Question;

import java.util.ArrayList;
import java.util.Objects;

public final class VariablesReferencesValidator {

    public static void validateVariablesUsage(ArrayList<Question> questions, ArrayList<VariableReference> variableReferences) throws RuntimeException {
        for (VariableReference reference : variableReferences) {
            boolean found = false;
            for (Question question : questions) {

                if (Objects.equals(question.getVariableName(), reference.getName())) {
                    found = true;
                }
            }
            if (!found) {
                throw new RuntimeException("Reference to undeclared variable \"" + reference.getName() + "\" on line " + reference.getMetaInformation().getStartLine() + ".");
            }
        }
    }
}
