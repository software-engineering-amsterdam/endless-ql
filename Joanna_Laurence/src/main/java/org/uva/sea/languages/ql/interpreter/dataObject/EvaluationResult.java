package org.uva.sea.languages.ql.interpreter.dataObject;

import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.elements.Form;

import java.util.Collection;
import java.util.List;

public class EvaluationResult {

    private final Form ast;

    private final Messages messages;

    private final List<QuestionData> questions;

    public EvaluationResult(List<QuestionData> questions, Messages warnings, Form ast) {
        this.messages = warnings;
        this.questions = questions;
        this.ast = ast;
    }

    public Messages getMessages() {
        return this.messages;
    }

    public Collection<QuestionData> getQuestions() {
        return this.questions;
    }

    public void add(QuestionData questionRow) {
        this.questions.add(questionRow);
    }

    public Form getAst() {
        return this.ast;
    }
}
