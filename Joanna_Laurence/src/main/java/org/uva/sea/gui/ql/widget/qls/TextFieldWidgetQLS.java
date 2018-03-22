package org.uva.sea.gui.ql.widget.qls;

import javafx.scene.Node;
import org.uva.sea.gui.ql.widget.QLSWidget;
import org.uva.sea.gui.ql.widget.ql.CheckBoxWidget;
import org.uva.sea.gui.ql.widget.ql.TextFieldWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

public class TextFieldWidgetQLS extends QLSWidget {

    private final TextFieldWidget textFieldWidget;

    public TextFieldWidgetQLS(QuestionData questionData) {
        super(questionData);
        this.textFieldWidget = new TextFieldWidget(questionData);
    }

    @Override
    public Node convertToGuiNode() {
        return this.textFieldWidget.convertToGuiNode();
    }
}
