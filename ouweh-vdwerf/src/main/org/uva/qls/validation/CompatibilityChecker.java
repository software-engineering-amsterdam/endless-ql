package org.uva.qls.validation;

import org.uva.ql.ast.Question;
import org.uva.ql.ast.type.Type;
import org.uva.ql.validation.ValidationResult;
import org.uva.ql.validation.checker.Checker;
import org.uva.qls.ast.Segment.QuestionReference;
import org.uva.qls.ast.Widget.Widget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CompatibilityChecker extends Checker {

    private List<QuestionReference> qlsQuestions;
    private Map<String, Type> qlQuestionTypes = new HashMap<>();

    public CompatibilityChecker(List<Question> qlQuestions, List<QuestionReference> qlsQuestions) {
        for (Question question : qlQuestions) {
            qlQuestionTypes.put(question.getId(), question.getType());
        }
        this.qlsQuestions = qlsQuestions;
    }

    @Override
    public ValidationResult runCheck() {
        ValidationResult result = new ValidationResult();

        for (QuestionReference questionReference : qlsQuestions) {
            Type type = qlQuestionTypes.get(questionReference.getId());
            Widget widget = questionReference.getWidget();
            if (widget != null && !widget.isCompatible(type.toString())) {
                result.addError(String.format("Widget %s is not compatible with %s", widget.toString(), type.toString()));
                logger.severe(String.format("Widget %s is not compatible with %s", widget.toString(), type.toString()));
            }
        }

        return result;
    }
}
