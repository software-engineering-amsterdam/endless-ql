package gui.widgets;

import QL.classes.values.*;
import gui.panels.QuestionPanel;

public class WidgetFactory {
    public static Widget getDefaultWidget(Value value){
        switch (value.getType()) {
            case Value.STRING:
                return new TextWidget((StringValue) value);
            case Value.BOOLEAN:
                return new CheckBoxWidget((BooleanValue) value);
            case Value.DATE:
                return new DateWidget((DateValue) value);
            case Value.DECIMAL:
            case Value.MONEY:
            case Value.INTEGER:
                return new TextWidget((NumericValue) value);
        }

        return null;
    }
}
