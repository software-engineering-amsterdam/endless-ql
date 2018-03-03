package org.uva.sea.ql.dataObject;

import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.staticAnalysis.helpers.Messages;

import java.util.List;

public class InterpreterResult {

    private Messages warnings;

    private List<QuestionData> questions;

    public InterpreterResult(List<QuestionData> questions, Messages warnings) {
        this.warnings = warnings;
        this.questions = questions;
    }

    public Messages getWarnings() {
        return warnings;
    }

    public List<QuestionData> getQuestions() {
        return questions;
    }

    public void add(QuestionData questionRow) {
        questions.add(questionRow);
    }
}
