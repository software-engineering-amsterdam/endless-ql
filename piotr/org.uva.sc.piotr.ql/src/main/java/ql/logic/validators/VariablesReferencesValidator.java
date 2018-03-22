package ql.logic.validators;

import ql.ast.model.expressions.values.VariableReference;
import ql.ast.model.statements.Question;
import ql.error.Error;

import java.util.List;
import java.util.Objects;

public final class VariablesReferencesValidator extends Validator {

    private List<Question> questions;
    private List<VariableReference> variableReferences;

    public VariablesReferencesValidator(List<Question> questions, List<VariableReference> variableReferences) {
        this.questions = questions;
        this.variableReferences = variableReferences;
    }

    @Override
    public boolean validate() {
        for (VariableReference reference : variableReferences) {
            boolean found = false;
            for (Question question : questions) {

                if (Objects.equals(question.getVariableName(), reference.getName())) {
                    found = true;
                }
            }
            if (!found) {
                String message = "Reference to undeclared variable \"" + reference.getName() + "\" on line " + reference.getMetaInformation().getStartLine() + ".";
                this.setError(new Error(Error.Level.CRITICAL, message));
                return false;
            }
        }
        return true;
    }
}
