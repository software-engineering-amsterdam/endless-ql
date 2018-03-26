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

    public EvaluationResult(final List<QuestionData> questions, final Messages warnings, final Form ast) {
        this.messages = warnings;
        this.questions = questions;
        this.ast = ast;
    }

    public final Messages getMessages() {
        return this.messages;
    }

    public final Collection<QuestionData> getQuestions() {
        return this.questions;
    }

    public final void add(final QuestionData questionRow) {
        this.questions.add(questionRow);
    }

    public final Form getAst() {
        return this.ast;
    }
}
