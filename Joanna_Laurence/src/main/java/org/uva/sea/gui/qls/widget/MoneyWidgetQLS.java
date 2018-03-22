package org.uva.sea.gui.qls.widget;

import javafx.scene.Node;
import org.uva.sea.gui.widget.BaseWidget;
import org.uva.sea.gui.widget.MoneyWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

public class MoneyWidgetQLS extends WidgetQLS {

    private final BaseWidget moneyWidget;

    public MoneyWidgetQLS(QuestionData questionData) {
        super(questionData);
        this.moneyWidget = this.linkToOtherWidget(new MoneyWidget(questionData), questionData);
    }

    @Override
    public Node convertToGuiNode() {
        Node widget = this.moneyWidget.convertToGuiNode();
        this.setStyle(widget);
        return widget;
    }
}
