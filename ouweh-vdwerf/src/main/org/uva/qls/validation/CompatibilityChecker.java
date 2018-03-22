package org.uva.qls.validation;

import org.uva.ql.ast.Question;
import org.uva.ql.ast.type.Type;
import org.uva.ql.validation.ValidationResult;
import org.uva.ql.validation.checker.Checker;
import org.uva.qls.ast.DefaultStatement.DefaultWidgetStatement;
import org.uva.qls.ast.Segment.QuestionReference;
import org.uva.qls.ast.Widget.Widget;
import org.uva.qls.collector.StylesheetContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CompatibilityChecker extends Checker {

    private List<QuestionReference> qlsQuestions;
    private final StylesheetContext context;
    private Map<String, Type> qlQuestionTypes = new HashMap<>();

    public CompatibilityChecker(List<Question> qlQuestions, List<QuestionReference> qlsQuestions, StylesheetContext context) {
        this.context = context;
        for (Question question : qlQuestions) {
            qlQuestionTypes.put(question.getId(), question.getType());
        }
        this.qlsQuestions = qlsQuestions;
    }

    @Override
    public ValidationResult runCheck() {
        ValidationResult result = new ValidationResult();

        result = checkQuestionWidgetCompatibility(result);
        result = checkDefaultWidgetTypeCompatibility(result);

        return result;
    }

    private ValidationResult checkQuestionWidgetCompatibility(ValidationResult validationResult){
        for (QuestionReference questionReference : qlsQuestions) {
            Type type = qlQuestionTypes.get(questionReference.getId());
            Widget widget = questionReference.getWidget();
            if (widget != null && !widget.isCompatible(type.getClass())) {
                validationResult.addError(String.format("Widget %s is not compatible with %s", widget.toString(), type.toString()));
                logger.severe(String.format("Widget %s is not compatible with %s", widget.toString(), type.toString()));
            }
        }
        return validationResult;
    }

    private ValidationResult checkDefaultWidgetTypeCompatibility(ValidationResult validationResult){
        for(DefaultWidgetStatement defaultWidgetStatement: this.context.getAllDefaultWidgetStatements()) {
            Type type = defaultWidgetStatement.getType();
            Widget widget = defaultWidgetStatement.getWidget();
            if (!widget.isCompatible(type.getClass())) {
               validationResult.addError(String.format("Type %s is not compatible with widget %s", type.toString(), widget.toString()));
            }
        }
        return validationResult;
    }
}
