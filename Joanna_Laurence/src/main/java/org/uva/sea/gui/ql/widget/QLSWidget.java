package org.uva.sea.gui.ql.widget;

import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

public abstract class QLSWidget extends Widget {
    public QLSWidget(QuestionData questionData) {
        super(questionData);
    }

    @Override
    public String drawInContainer() {
        return this.questionData.getStyle().getPage();
    }
}
