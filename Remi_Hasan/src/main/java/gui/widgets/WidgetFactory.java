package gui.widgets;

import ql.model.expression.ReturnType;

public class WidgetFactory {
    // TODO: implement interface that can be overidden by QLS
    public static GUIWidget getDefaultWidget(ReturnType questionType) {
        switch (questionType) {
            case STRING:
                return new TextWidget();
            case INTEGER:
                return new IntegerWidget();
            case DECIMAL:
                return new DecimalWidget();
            case MONEY:
                return new MoneyWidget();
            case DATE:
                return new DateWidget();
            case BOOLEAN:
                return new CheckboxWidget();
            default:
                throw new UnsupportedOperationException("Question type not implemented to render in GUI");
        }
    }
}
