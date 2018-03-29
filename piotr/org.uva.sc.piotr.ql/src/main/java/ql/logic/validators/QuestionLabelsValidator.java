package ql.logic.validators;

import ql.ast.model.statements.Question;
import ql.error.Error;

import java.util.List;
import java.util.Objects;

public final class QuestionLabelsValidator extends Validator {

    private final List<Question> questions;

    public QuestionLabelsValidator(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean validate() {
        for (Question question1 : this.questions) {
            for (Question question2 : this.questions) {
                if (!Objects.equals(question1, question2)
                        && Objects.equals(question1.getLabel(), question2.getLabel())) {
                    String message = "Duplicate label declarations in lines : "
                            + question1.getMetaInformation().getStartLine()
                            + " and "
                            + question2.getMetaInformation().getStartLine();
                    this.setError(new Error(Error.Level.WARNING, message));
                    return false;
                }
            }
        }
        return true;
    }

}
