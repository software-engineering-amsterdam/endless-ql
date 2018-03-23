package gui.widgets;

import gui.widgets.textbox.TextboxDecimalWidget;
import gui.widgets.textbox.TextboxIntegerWidget;
import gui.widgets.textbox.TextboxMoneyWidget;
import gui.widgets.textbox.TextboxWidget;
import ql.model.expression.ReturnType;

public class WidgetFactory {
    // TODO: implement interface that can be overridden by QLS
    public static GUIWidget getDefaultWidget(ReturnType questionType) {
        switch (questionType) {
            case STRING:
                return new TextboxWidget();
            case INTEGER:
                return new TextboxIntegerWidget();
            case DECIMAL:
                return new TextboxDecimalWidget();
            case MONEY:
                return new TextboxMoneyWidget();
            case DATE:
                return new DateWidget();
            case BOOLEAN:
                return new DropdownWidget("no", "yes");
            default:
                throw new UnsupportedOperationException("Question type not implemented to render in GUI");
        }
    }
}
