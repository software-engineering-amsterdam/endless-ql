package qls.model.widget;

import ql.model.expression.ReturnType;

public enum WidgetType {
    SLIDER,
    SPINBOX,
    TEXTBOX,
    RADIO,
    CHECKBOX,
    DROPDOWN,
    INTEGER,
    DECIMAL,
    MONEY,
    STRING,
    DATE,
    DEFAULT;

    public boolean isCompatible(ReturnType returnType) {
        switch (returnType) {
            case BOOLEAN:
                return this == CHECKBOX || this == DROPDOWN || this == RADIO;
            case STRING:
                return this == TEXTBOX;
            case INTEGER:
            case DECIMAL:
            case MONEY:
                return this == SLIDER || this == SPINBOX || this == TEXTBOX;
            case DATE:
                return false;
        }

        return false;
    }


}
