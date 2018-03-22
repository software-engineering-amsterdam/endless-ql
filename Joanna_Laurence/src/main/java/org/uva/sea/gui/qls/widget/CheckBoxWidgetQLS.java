package org.uva.sea.gui.qls.widget;

import javafx.scene.Node;
import org.uva.sea.gui.widget.BaseWidget;
import org.uva.sea.gui.widget.CheckBoxWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

public class CheckBoxWidgetQLS extends QLSWidget {

    private final BaseWidget checkBoxWidget;

    public CheckBoxWidgetQLS(QuestionData questionData) {
        super(questionData);
        this.checkBoxWidget = this.linkToOtherWidget(new CheckBoxWidget(questionData), questionData);
    }

    @Override
    public Node convertToGuiNode() {
        return this.checkBoxWidget.convertToGuiNode();
    }
}
