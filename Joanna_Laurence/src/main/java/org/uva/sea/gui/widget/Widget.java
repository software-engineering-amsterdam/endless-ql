package org.uva.sea.gui.widget;

import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

public abstract class Widget extends BaseWidget {
    Widget(final QuestionData questionData) {
        super(questionData);
    }

    @Override
    public final String getContainerName() {
        return "default";
    }
}
