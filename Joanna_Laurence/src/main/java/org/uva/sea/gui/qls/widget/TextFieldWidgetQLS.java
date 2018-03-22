package org.uva.sea.gui.qls.widget;

import javafx.scene.Node;
import org.uva.sea.gui.widget.BaseWidget;
import org.uva.sea.gui.widget.TextFieldWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

public class TextFieldWidgetQLS extends QLSWidget {

    private final BaseWidget textFieldWidget;

    public TextFieldWidgetQLS(QuestionData questionData) {
        super(questionData);
        this.textFieldWidget = this.linkToOtherWidget(new TextFieldWidget(questionData), questionData);
    }

    @Override
    public Node convertToGuiNode() {
        return this.textFieldWidget.convertToGuiNode();
    }
}
