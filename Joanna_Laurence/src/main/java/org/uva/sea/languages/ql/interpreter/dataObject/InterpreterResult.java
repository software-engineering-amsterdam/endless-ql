package org.uva.sea.languages.ql.interpreter.dataObject;

import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;

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
