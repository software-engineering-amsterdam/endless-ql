package org.uva.sea.gui.widget;

import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

abstract class Widget extends BaseWidget {
    Widget(QuestionData questionData) {
        super(questionData);
    }

    @Override
    public String getContainerName() {
        return "default";
    }
}
