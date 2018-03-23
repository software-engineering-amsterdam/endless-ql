package gui.widgets;

import gui.widgets.textbox.TextboxDecimalWidget;
import gui.widgets.textbox.TextboxIntegerWidget;
import gui.widgets.textbox.TextboxMoneyWidget;
import gui.widgets.textbox.TextboxWidget;
import ql.model.expression.ReturnType;

public class WidgetFactory {
    // TODO: implement interface that can be overridden by QLS
    public static GUIWidget getDefaultWidget(String identifier, boolean computed, ReturnType questionType) {
        switch (questionType) {
            case STRING:
                return new TextboxWidget(identifier, computed);
            case INTEGER:
                return new TextboxIntegerWidget(identifier, computed);
            case DECIMAL:
                return new TextboxDecimalWidget(identifier, computed);
            case MONEY:
                return new TextboxMoneyWidget(identifier, computed);
            case DATE:
                return new DateWidget(identifier, computed);
            case BOOLEAN:
                return new DropdownWidget(identifier, computed, "no", "yes");
            default:
                throw new UnsupportedOperationException("Question type not implemented to render in GUI");
        }
    }
}
