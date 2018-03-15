package org.uva.sea.languages.ql.interpreter.dataObject;

public enum WidgetType {
    INVALID,
    UNKNOWN,

    CHECKBOX,
    CHOICEBOX,
    RADIO,
    SLIDER,
    SPINBOX,
    TEXTFIELD,

    MONEY_EURO,
    MONEY_DOLLAR,
    BOOLEAN,
    STRING,
    INTEGER,
    DATE,
    DECIMAL;

    public boolean isBooleanCompatible(WidgetType widgetType) {
        return widgetType == CHECKBOX ||
                widgetType == CHOICEBOX ||
                widgetType == RADIO ||
                widgetType == BOOLEAN;
    }

    public boolean isNumberCompatible(WidgetType widgetType) {
        return widgetType == SLIDER ||
                widgetType == SPINBOX ||
                widgetType == INTEGER ||
                widgetType == DECIMAL;
    }

    public boolean isStringCompatible(WidgetType widgetType) {
        return widgetType == TEXTFIELD ||
                widgetType == STRING ||
                widgetType == INTEGER ||
                widgetType == DECIMAL;
    }

    private boolean isNotUnknown(WidgetType widgetType) {
        return widgetType != UNKNOWN;
    }

    public boolean isCompatible(WidgetType widgetType) {
        return  isNotUnknown(widgetType) && isNotUnknown(this) && (
                    this == widgetType ||
                    isBooleanCompatible(this) && isBooleanCompatible(widgetType) ||
                    isNumberCompatible(this) && isNumberCompatible(widgetType) ||
                    isStringCompatible(this) && isStringCompatible(widgetType)
                );
    }
}
