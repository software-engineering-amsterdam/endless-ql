package org.uva.sea.gui.qls.widget;

import javafx.scene.Node;
import org.uva.sea.gui.widget.BaseWidget;
import org.uva.sea.gui.widget.DateWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

public class DateWidgetQLS extends WidgetQLS {

    private final BaseWidget widget;

    public DateWidgetQLS(final QuestionData questionData) {
        super(questionData);
        this.widget = this.linkToOtherWidget(new DateWidget(questionData), questionData);
    }

    @Override
    public final Node convertToGuiNode() {
        final Node widget = this.widget.convertToGuiNode();
        this.setStyle(widget);
        return widget;
    }
}
