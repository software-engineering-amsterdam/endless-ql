package org.uva.sea.languages.ql.interpreter.dataObject;

import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;
import org.uva.sea.languages.ql.parser.elements.Form;

import java.util.List;

public class EvaluationResult {

    private Form ast;

    private Messages messages;

    private List<QuestionData> questions;

    public EvaluationResult(List<QuestionData> questions, Messages warnings, Form ast) {
        this.messages = warnings;
        this.questions = questions;
        this.ast = ast;
    }

    public Messages getMessages() {
        return messages;
    }

    public List<QuestionData> getQuestions() {
        return questions;
    }

    public void add(QuestionData questionRow) {
        questions.add(questionRow);
    }

    public Form getAst() {
        return ast;
    }
}
