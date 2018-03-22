package org.uva.sea.gui.qls.widget;

import org.uva.sea.gui.widget.BaseWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

public abstract class QLSWidget extends BaseWidget {
    public QLSWidget(QuestionData questionData) {
        super(questionData);
    }

    @Override
    public String drawInContainer() {
        return this.questionData.getStyle().getPage();
    }
}
