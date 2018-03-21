package org.uva.ql.validation.checker;

import org.uva.ql.ast.Question;
import org.uva.ql.validation.ValidationResult;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionChecker extends Checker {


    private List<Question> questions;

    public QuestionChecker(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public ValidationResult runCheck() {
        ValidationResult result = new ValidationResult();

        Set<String> questionIDs = new HashSet<>();
        Set<String> questionTexts = new HashSet<>();

        for (Question question : this.questions) {
            if (!questionIDs.add(question.getId())) {
                String message = String.format("WARNING: (var could be overwritten) question name %s already exists", question.getId());

                result.addWarning(message);
                logger.warning(message);
            }

            if (!questionTexts.add(question.getContent())) {
                String message = String.format("WARNING: Question content %s already exists", question.getContent());

                result.addWarning(message);
                logger.warning(message);
            }
        }

        return result;
    }
}
