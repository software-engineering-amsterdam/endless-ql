package org.uva.ql.gui;

import org.uva.ql.evaluator.value.Value;

public class QuestionChangeListener {

    private final GUIHandler guiHandler;

    public QuestionChangeListener(GUIHandler guiHandler) {
        this.guiHandler = guiHandler;
    }

    public void onQuestionChanged(String id, Value value) {
        this.guiHandler.onQuestionChange(id, value);
    }
}
