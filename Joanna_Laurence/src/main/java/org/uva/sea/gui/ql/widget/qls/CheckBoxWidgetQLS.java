package org.uva.sea.gui.ql.widget.qls;

import javafx.scene.Node;
import javafx.scene.control.Control;
import org.uva.sea.gui.ql.IGuiElementUpdateListener;
import org.uva.sea.gui.ql.widget.QLSWidget;
import org.uva.sea.gui.ql.widget.ql.CheckBoxWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

public class CheckBoxWidgetQLS extends QLSWidget {

    private final CheckBoxWidget checkBoxWidget;

    public CheckBoxWidgetQLS(QuestionData questionData) {
        super(questionData);
        this.checkBoxWidget = new CheckBoxWidget(questionData);
        this.checkBoxWidget.addListener(this::sendUpdateValueEvent);
    }

    @Override
    public Node convertToGuiNode() {
        return this.checkBoxWidget.convertToGuiNode();
    }
}
