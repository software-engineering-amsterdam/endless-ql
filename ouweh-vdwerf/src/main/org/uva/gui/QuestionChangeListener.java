package org.uva.gui;

import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.Value;

public class QuestionChangeListener {

    private final GUIHandler guiHandler;

    public QuestionChangeListener(GUIHandler guiHandler) {
        this.guiHandler = guiHandler;
    }

    public void onQuestionChanged(Question question, Value value) {
        this.guiHandler.onQuestionChange(question, value);
    }
}
