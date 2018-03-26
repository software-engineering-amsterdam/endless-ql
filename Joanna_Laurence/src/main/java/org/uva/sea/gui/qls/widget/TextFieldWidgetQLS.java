package org.uva.sea.gui.qls.widget;

import javafx.scene.Node;
import org.uva.sea.gui.widget.BaseWidget;
import org.uva.sea.gui.widget.TextFieldWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

public class TextFieldWidgetQLS extends WidgetQLS {

    private final BaseWidget textFieldWidget;

    public TextFieldWidgetQLS(final QuestionData questionData) {
        super(questionData);
        this.textFieldWidget = this.linkToOtherWidget(new TextFieldWidget(questionData), questionData);
    }

    @Override
    public final Node convertToGuiNode() {
        final Node widget = this.textFieldWidget.convertToGuiNode();
        this.setStyle(widget);
        return widget;
    }
}
