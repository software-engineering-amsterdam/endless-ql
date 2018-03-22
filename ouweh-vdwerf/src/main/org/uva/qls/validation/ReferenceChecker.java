package org.uva.qls.validation;

import org.uva.ql.ast.Question;
import org.uva.ql.validation.ValidationResult;
import org.uva.ql.validation.checker.Checker;
import org.uva.qls.ast.Segment.QuestionReference;
import org.uva.qls.ast.Segment.Stylesheet;
import org.uva.qls.collector.StylesheetContext;
import org.uva.ql.validation.collector.QuestionContext;

import org.uva.ql.ast.Form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

class ReferenceChecker extends Checker {

    private List<String> qlQuestionIds;
    private List<String> qlsQuestionIds;

    public ReferenceChecker(QuestionContext questionContext, StylesheetContext stylesheetContext) {
        this.qlQuestionIds = questionContext.getQuestions().stream().map(Question::getId).collect(Collectors.toList());
        this.qlsQuestionIds = stylesheetContext.getQuestions().stream().map(QuestionReference::getQuestionId).collect(Collectors.toList());


        Collections.sort(this.qlQuestionIds);
        Collections.sort(this.qlsQuestionIds);
    }

    @Override
    public ValidationResult runCheck() {
        ValidationResult result = new ValidationResult();

        HashSet uniqueIds = new HashSet();
        List<String> duplicateIds = this.qlsQuestionIds.stream()
                .filter(e -> !uniqueIds.add(e))
                .collect(Collectors.toList());
        if (duplicateIds.size() > 0) {
            result.addError(String.format("Questions: %s are referenced multiple times by the QLS", duplicateIds.toString()));
        }

        List qlsQuestionIdsCopy = new ArrayList(qlsQuestionIds);
        qlsQuestionIdsCopy.removeAll(qlQuestionIds);

        if (qlsQuestionIdsCopy.size() > 0) {
            result.addError(String.format("Questions:%s are in QLS but not in QL", qlsQuestionIdsCopy.toString()));
        }

        qlQuestionIds.removeAll(qlsQuestionIds);
        if (qlQuestionIds.size() > 0) {
            result.addError(String.format("Questions: %s are in QL but not in QLS", qlQuestionIds.toString()));
        }

        return result;
    }
}
