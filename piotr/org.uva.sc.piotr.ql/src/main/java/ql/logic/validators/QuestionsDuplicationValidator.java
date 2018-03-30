package ql.logic.validators;

import ql.ast.model.statements.Question;
import ql.error.Error;

import java.util.List;
import java.util.Objects;

public class QuestionsDuplicationValidator extends Validator {

    private final List<Question> questions;

    public QuestionsDuplicationValidator(List<Question> questions) {
        this.questions = questions;
    }

    public boolean validate() {
        for (Question question1 : this.questions) {
            for (Question question2 : this.questions) {
                if (!Objects.equals(question1, question2)
                        && Objects.equals(question1.getVariableName(), question2.getVariableName())
                        && !Objects.equals(question1.getVariableType().getIdentifier(), question2.getVariableType().getIdentifier())) {

                    String message = "Duplicate question declarations with different types in lines : "
                            + question1.getMetaInformation().getStartLine()
                            + " and "
                            + question2.getMetaInformation().getStartLine();
                    this.setError(new Error(Error.Level.CRITICAL, message));
                    return false;
                }
            }
        }
        return true;
    }
}
