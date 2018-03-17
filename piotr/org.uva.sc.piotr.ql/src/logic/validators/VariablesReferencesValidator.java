package logic.validators;

import ast.model.expressions.values.VariableReference;
import ast.model.statements.Question;

import java.util.List;
import java.util.Objects;

public final class VariablesReferencesValidator {

    public static void validateVariablesUsage(List<Question> questions, List<VariableReference> variableReferences) throws RuntimeException {
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
