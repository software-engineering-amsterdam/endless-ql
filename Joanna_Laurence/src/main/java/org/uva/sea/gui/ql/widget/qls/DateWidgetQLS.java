package org.uva.sea.gui.ql.widget.qls;

import javafx.scene.Node;
import org.uva.sea.gui.ql.widget.QLSWidget;
import org.uva.sea.gui.ql.widget.Widget;
import org.uva.sea.gui.ql.widget.ql.DateWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

public class DateWidgetQLS extends QLSWidget {

    private final Widget widget;

    public DateWidgetQLS(QuestionData questionData) {
        super(questionData);
        this.widget = this.linkToOtherWidget(new DateWidget(questionData), questionData);
    }

    @Override
    public Node convertToGuiNode() {
        return this.widget.convertToGuiNode();
    }
}
