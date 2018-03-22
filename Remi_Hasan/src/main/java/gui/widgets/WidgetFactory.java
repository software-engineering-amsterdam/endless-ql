package gui.widgets;

import ql.model.expression.ReturnType;

public class WidgetFactory {
    // TODO: implement interface that can be overridden by QLS
    public static GUIWidget getDefaultWidget(ReturnType questionType) {
        switch (questionType) {
            case STRING:
                return new TextWidget();
            case INTEGER:
                return new SliderIntegerWidget(0, 3);
            case DECIMAL:
                return new SliderDecimalWidget(0, 10);
            case MONEY:
                return new SliderMoneyWidget(0, 10);
            case DATE:
                return new DateWidget();
            case BOOLEAN:
                return new CheckboxWidget();
            default:
                throw new UnsupportedOperationException("Question type not implemented to render in GUI");
        }
    }
}
