package gui.widgets;

import QL.classes.values.BooleanValue;
import QL.classes.values.DateValue;
import QL.classes.values.NumericValue;
import QL.classes.values.StringValue;
import QL.classes.values.Value;

public class WidgetFactory {
    public static Widget getDefaultWidget(Value value) {
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
            default: break;
        }
        return null;
    }
}
