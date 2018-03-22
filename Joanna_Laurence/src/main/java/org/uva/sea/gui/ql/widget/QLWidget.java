package org.uva.sea.gui.ql.widget;

import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

public abstract class QLWidget extends Widget {
    public QLWidget(QuestionData questionData) {
        super(questionData);
    }

    @Override
    public String drawInContainer() {
        return "default";
    }
}
